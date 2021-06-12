package com.boot.cafemanager.service.order.dto;

import com.boot.cafemanager.service.table.dto.TableDTO;
import com.boot.cafemanager.types.enums.OrderType;

import java.util.List;

public class OrderDTO {

    private Long id;

    private List<ProductInOrderDTO> productInOrders;

    private OrderType status;

    private TableDTO table;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductInOrderDTO> getProductInOrders() {
        return productInOrders;
    }

    public void setProductInOrders(List<ProductInOrderDTO> productInOrders) {
        this.productInOrders = productInOrders;
    }

    public OrderType getStatus() {
        return status;
    }

    public void setStatus(OrderType status) {
        this.status = status;
    }

    public TableDTO getTable() {
        return table;
    }

    public void setTable(TableDTO table) {
        this.table = table;
    }
}
