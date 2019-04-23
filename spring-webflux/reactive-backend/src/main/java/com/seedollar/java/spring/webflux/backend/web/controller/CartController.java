package com.seedollar.java.spring.webflux.backend.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class CartController {

  private static final String BASE_MOCKS_PATH = "src/test/resources/mocks/";

  @PostMapping("/cart")
  public Mono<ResponseEntity<String>> getCart(@RequestBody Object body) throws IOException {
    return Mono
        .just(new ResponseEntity<>(getBodyAsPlainText("cart-response.json"), HttpStatus.CREATED));
  }

  protected static String getBodyAsPlainText(String relativePath) throws IOException {
    Path absPath = Paths.get(ResourceUtils.getFile(BASE_MOCKS_PATH + relativePath).toURI());
    byte[] fileBytes = Files.readAllBytes(absPath);
    return new String(fileBytes);
  }
}
