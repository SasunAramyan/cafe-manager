package com.boot.cafemanager.web.rest.model.product;

import com.boot.cafemanager.service.product.dto.ProductDTO;

public class ProductInOrderModel {

    private Long id;

    private ProductModel product;

    private Long count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
