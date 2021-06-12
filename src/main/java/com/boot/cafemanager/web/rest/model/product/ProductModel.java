package com.boot.cafemanager.web.rest.model.product;

import com.boot.cafemanager.service.order.dto.ProductInOrderDTO;
import com.boot.cafemanager.service.table.dto.TableDTO;
import com.boot.cafemanager.types.enums.OrderType;

import java.util.List;

public class ProductModel {

    private Long id;

    private List<ProductInOrderModel> productInOrders;

    private OrderType status;

    private TableDTO table;
}
