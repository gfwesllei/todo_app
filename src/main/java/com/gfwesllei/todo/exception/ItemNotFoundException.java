package com.gfwesllei.todo.exception;

public class ItemNotFoundException extends RuntimeException{

  public ItemNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
