package com.boot.cafemanager.service.order.dto;

import com.boot.cafemanager.service.product.dto.ProductDTO;

public class ProductInOrderDTO {

    private Long id;

    private ProductDTO product;

    private Long count;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
