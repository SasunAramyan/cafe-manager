package com.boot.cafemanager.exception;


public class ProductNotFoundException extends ResourceNotFoundException {

  private static final String CLASS_NAME = "Product";

  public ProductNotFoundException(Long id) {
    super(CLASS_NAME, id);
  }

}
