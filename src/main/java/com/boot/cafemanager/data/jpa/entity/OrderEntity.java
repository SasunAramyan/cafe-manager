package com.boot.cafemanager.data.jpa.entity;

import com.boot.cafemanager.types.enums.OrderType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Table(name = "`order`")
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId", nullable = false)
    private List<ProductInOrderEntity> productInOrders;

    @Column(nullable = false)
    private OrderType status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tableId")
    private TableEntity table;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductInOrderEntity> getProductInOrders() {
        return productInOrders;
    }

    public void setProductInOrders(List<ProductInOrderEntity> productInOrders) {
        this.productInOrders = productInOrders;
    }

    public OrderType getStatus() {
        return status;
    }

    public void setStatus(OrderType status) {
        this.status = status;
    }

    public TableEntity getTable() {
        return table;
    }

    public void setTable(TableEntity table) {
        this.table = table;
    }
}
