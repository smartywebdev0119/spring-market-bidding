package com.tradindemboiz.spring.controllers;

import com.tradindemboiz.spring.entities.User;
import com.tradindemboiz.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    var users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }
}
