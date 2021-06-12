package com.boot.cafemanager.data.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "order")
@Entity
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @ManyToMany
  @JoinTable(
      name = "productInOrder",
      joinColumns = @JoinColumn(name = "orderId"),
      inverseJoinColumns = @JoinColumn(name = "productId"))
  private List<ProductEntity> products;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<ProductEntity> getProducts() {
    return products;
  }

  public void setProducts(List<ProductEntity> products) {
    this.products = products;
  }

}
