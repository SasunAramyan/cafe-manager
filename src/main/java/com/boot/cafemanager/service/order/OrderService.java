package com.boot.cafemanager.service.order;

import com.boot.cafemanager.service.order.dto.OrderCreateDTO;
import com.boot.cafemanager.service.order.dto.OrderDTO;
import com.boot.cafemanager.service.order.dto.OrderEditDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrEdit(OrderCreateDTO createDTO);

    OrderDTO getById(Long id);

    List<OrderDTO> getAll();

    void edit(OrderEditDTO editDTO);

    void changeStatusToComplete(Long orderId);

    void changeStatusToCanceled(Long orderId);

}
