package com.boot.cafemanager.web.rest.model.product;

import javax.validation.constraints.NotNull;

public class ProductInOrderCreateModel {

    @NotNull
    private Long productId;

    @NotNull
    private Long count;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
