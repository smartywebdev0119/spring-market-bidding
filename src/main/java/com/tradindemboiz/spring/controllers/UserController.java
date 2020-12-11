package com.tradindemboiz.spring.controllers;

import com.tradindemboiz.spring.dto.UserDto;
import com.tradindemboiz.spring.entities.User;
import com.tradindemboiz.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

  @PostMapping
  public ResponseEntity<User> registerUser(@RequestBody @Validated UserDto userToBeCreated) {
    var newUser = userService.registerUser(userToBeCreated);
    var uri = URI.create("/api/v1/users/" + newUser.getUser_id());
    return ResponseEntity.created(uri).body(newUser);
  }
}
