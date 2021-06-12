package com.boot.cafemanager.exception;

public abstract class ResourceNotFoundException extends RuntimeException {

  protected <T extends Class<?>> ResourceNotFoundException(String typeName, long id) {

    super(String.format("%s not found: id = %s", typeName, id));
  }

  protected <T extends Class<?>> ResourceNotFoundException(T type, String message) {
    super(String.format("%s not found, %s", type.getSimpleName(), message));
  }
}
