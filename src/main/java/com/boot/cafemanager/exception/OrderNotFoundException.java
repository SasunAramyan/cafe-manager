package com.boot.cafemanager.exception;


public class OrderNotFoundException extends ResourceNotFoundException {

  private static final String CLASS_NAME = "Order";

  public OrderNotFoundException(Long id) {
    super(CLASS_NAME, id);
  }

}
