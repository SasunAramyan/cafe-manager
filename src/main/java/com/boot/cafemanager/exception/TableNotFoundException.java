package com.boot.cafemanager.exception;


public class TableNotFoundException extends ResourceNotFoundException {

  private static final String CLASS_NAME = "Table";

  public TableNotFoundException(Long id) {
    super(CLASS_NAME, id);
  }

}
