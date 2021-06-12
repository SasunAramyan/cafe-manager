package com.boot.cafemanager.service.order;

import com.boot.cafemanager.data.jpa.entity.OrderEntity;
import com.boot.cafemanager.data.jpa.entity.ProductEntity;
import com.boot.cafemanager.data.jpa.entity.ProductInOrderEntity;
import com.boot.cafemanager.data.jpa.entity.TableEntity;
import com.boot.cafemanager.data.jpa.repository.OrderRepository;
import com.boot.cafemanager.data.jpa.repository.ProductRepository;
import com.boot.cafemanager.data.jpa.repository.TableRepository;
import com.boot.cafemanager.exception.OrderNotFoundException;
import com.boot.cafemanager.exception.ProductNotFoundException;
import com.boot.cafemanager.exception.TableNotFoundException;
import com.boot.cafemanager.mapper.OrderMapper;
import com.boot.cafemanager.service.order.dto.OrderCreateDTO;
import com.boot.cafemanager.service.order.dto.OrderDTO;
import com.boot.cafemanager.service.order.dto.OrderEditDTO;
import com.boot.cafemanager.types.enums.OrderType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, TableRepository tableRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public OrderDTO createOrEdit(OrderCreateDTO createDTO) {
        OrderEntity orderEntity = orderRepository.findByTable_IdAndStatus(createDTO.getTableId(), OrderType.OPEN)
                .orElse(new OrderEntity());
        setFields(orderEntity, createDTO);
        orderEntity.setStatus(OrderType.OPEN);
        orderRepository.save(orderEntity);
        return orderMapper.toDTO(orderEntity);
    }


    @Override
    public OrderDTO getById(Long id) {
        OrderEntity orderEntity = getOrderById(id);
        return orderMapper.toDTO(orderEntity);
    }


    @Override
    public List<OrderDTO> getAll() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void edit(OrderEditDTO editDTO) {
        OrderEntity orderEntity = getOrderById(editDTO.getId());
        setFields(orderEntity, editDTO);
        orderEntity.setStatus(editDTO.getStatus());
        orderRepository.save(orderEntity);
    }

    @Override
    public void changeStatusToComplete(Long orderId) {
        changeStatus(orderId, OrderType.COMPLETE);
    }

    @Override
    public void changeStatusToCanceled(Long orderId) {
        changeStatus(orderId, OrderType.CANCELED);
    }

    void changeStatus(Long orderId, OrderType status) {
        OrderEntity orderEntity = getOrderById(orderId);
        orderEntity.setStatus(status);
        orderRepository.save(orderEntity);
    }

    private void setFields(OrderEntity orderEntity, OrderCreateDTO createDTO) {
        setTable(orderEntity, createDTO);
        setProductInOrders(orderEntity, createDTO);
    }

    private void setProductInOrders(OrderEntity orderEntity, OrderCreateDTO createDTO) {
        List<ProductInOrderEntity> productInOrderEntities = createDTO.getProductInOrders().stream().map(p -> {
            ProductInOrderEntity productInOrderEntity = new ProductInOrderEntity();
            ProductEntity productEntity = productRepository.findById(p.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(p.getProductId()));
            productInOrderEntity.setProduct(productEntity);
            productInOrderEntity.setCount(p.getCount());
            return productInOrderEntity;
        }).collect(Collectors.toList());
        orderEntity.setProductInOrders(productInOrderEntities);

    }

    private void setTable(OrderEntity orderEntity, OrderCreateDTO createDTO) {
        TableEntity tableEntity = tableRepository.findByIdAndDeletedIsFalse(createDTO.getTableId())
                .orElseThrow(() -> new TableNotFoundException(createDTO.getTableId()));
        orderEntity.setTable(tableEntity);
    }

    private OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }
}
