package com.boot.cafemanager.exception;

public class UserNotFoundException extends ResourceNotFoundException {

  private static final String USER = "USER";

  public UserNotFoundException(Long id) {
    super(USER, id);
  }

}
