package com.gfwesllei.todo.controller;

import com.gfwesllei.todo.exception.ItemNotFoundException;
import jakarta.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

  @Data
  @AllArgsConstructor
  class Todo {

    private Long id;
    private String name;
  }

  private Map<Long, Todo> todoMap;

  @PostConstruct
  void createTodo() {
    todoMap = new LinkedHashMap();
    todoMap.put(1L, new Todo(1L, "task1"));
    todoMap.put(2L, new Todo(2L, "task2"));
  }

  @GetMapping(path = "list")
  ResponseEntity<List<Todo>> list() {
    return ResponseEntity.ok(todoMap.values().stream().toList());
  }

  @GetMapping(path = "{id}")
  ResponseEntity<Todo> getById(@PathVariable("id") Long id) {
    Todo todoItem = Optional.ofNullable(todoMap.get(id))
        .orElseThrow(() -> new ItemNotFoundException("Item not found"));
    return ResponseEntity.ok(todoItem);
  }

}
