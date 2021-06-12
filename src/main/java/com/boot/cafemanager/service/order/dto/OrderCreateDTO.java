package com.boot.cafemanager.service.order.dto;

import java.util.List;

public class OrderCreateDTO {

    private List<ProductInOrderCreateDTO> productInOrders;

    private Long tableId;

    public List<ProductInOrderCreateDTO> getProductInOrders() {
        return productInOrders;
    }

    public void setProductInOrders(List<ProductInOrderCreateDTO> productInOrders) {
        this.productInOrders = productInOrders;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
}
