package com.boot.cafemanager.web.rest.controller;

import com.boot.cafemanager.mapper.OrderMapper;
import com.boot.cafemanager.service.order.OrderService;
import com.boot.cafemanager.service.order.dto.OrderCreateDTO;
import com.boot.cafemanager.service.order.dto.OrderDTO;
import com.boot.cafemanager.web.rest.model.order.OrderCreateModel;
import com.boot.cafemanager.web.rest.model.order.OrderModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public ResponseEntity<OrderModel> create(@RequestBody OrderCreateModel orderCreateModel) {
        OrderCreateDTO orderCreateDTO = orderMapper.toDTO(orderCreateModel);
        OrderDTO orderDTO = orderService.createOrEdit(orderCreateDTO);
        OrderModel orderModel = orderMapper.toModel(orderDTO);
        return ResponseEntity.ok(orderModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderModel> getById(@PathVariable Long id) {
        OrderDTO orderDTO = orderService.getById(id);
        OrderModel orderModel = orderMapper.toModel(orderDTO);
        return ResponseEntity.ok(orderModel);
    }

    @PostMapping("/{id}/complete")
    public void changeStatusToComplete(@PathVariable Long id) {
        orderService.changeStatusToComplete(id);
    }

    @PostMapping("/{id}/cancel")
    public void changeStatusToCanceled(@PathVariable Long id) {
        orderService.changeStatusToCanceled(id);
    }

    @GetMapping
    public ResponseEntity<List<OrderModel>> getAll() {
        List<OrderDTO> orderDTOs = orderService.getAll();
        List<OrderModel> orderModels = orderDTOs.stream().map(orderMapper::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(orderModels);
    }


}
