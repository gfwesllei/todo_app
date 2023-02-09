package com.gfwesllei.todo.controller.advice;

import com.gfwesllei.todo.exception.ItemNotFoundException;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TodoControllerAdvice {

  @ExceptionHandler(ItemNotFoundException.class)
  private ResponseEntity errorResponseResponseEntity(
      ItemNotFoundException ex) {
    return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
  }

}
