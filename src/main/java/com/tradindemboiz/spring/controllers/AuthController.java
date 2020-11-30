package com.tradindemboiz.spring.controllers;

import com.tradindemboiz.spring.entities.User;
import com.tradindemboiz.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  UserService userService;

  @GetMapping("/whoami")
  public ResponseEntity<User> whoami() {
    User user = userService.getCurrentUser();
    if (user == null) {
      User anonymusUser = new User("anonymous");
      return ResponseEntity.ok(anonymusUser);
    }
    return ResponseEntity.ok(user);
  }
}
