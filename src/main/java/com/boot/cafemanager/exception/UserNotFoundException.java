package com.boot.cafemanager.exception;

public class UserNotFoundException extends ResourceNotFoundException {

  private static final String USER = "User";

  public UserNotFoundException(Long id) {
    super(USER, id);
  }
  public UserNotFoundException(String message) {
    super(USER, message);
  }

}
