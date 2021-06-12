package com.boot.cafemanager.service.order.dto;

import com.boot.cafemanager.types.enums.OrderType;

public class OrderEditDTO extends OrderCreateDTO {

    private Long id;

    private OrderType status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderType getStatus() {
        return status;
    }

    public void setStatus(OrderType status) {
        this.status = status;
    }
}
