package com.gfwesllei.todo.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TodoTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void shouldReturnListOfItems() {
    webTestClient.get().uri("api/todo/list")
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody()
        .jsonPath("$.length()").isEqualTo(2)
        .jsonPath("$[0].id").isEqualTo(1L)
        .jsonPath("$[0].name").isEqualTo("task1");
  }

  @Test
  void shouldReturn200WhenItemExists() {
    Long id=1L;
    webTestClient.get().uri("api/todo/{id}",id)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody()
        .jsonPath("$.id").isEqualTo(id)
        .jsonPath("$.name").isEqualTo("task1");
  }

  @Test
  void shouldReturn404WhenItemNotExists() {
    Long id=10L;
    webTestClient.get().uri("api/todo/{id}",id)
        .exchange()
        .expectStatus().isEqualTo(BAD_REQUEST)
        .expectBody()
        .jsonPath("$.error").isEqualTo("Item not found");
  }
}
