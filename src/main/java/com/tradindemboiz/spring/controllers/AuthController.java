package com.tradindemboiz.spring.controllers;

import com.tradindemboiz.spring.entities.User;
import com.tradindemboiz.spring.entities.UserDto;
import com.tradindemboiz.spring.models.LoginDto;
import com.tradindemboiz.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

  @PostMapping("/loginUser")
  public ResponseEntity<String> loginUser(@RequestBody UserDto userDto, HttpServletRequest req){
    userService.loginUser(userDto, req);
    return ResponseEntity.ok("OK");
  }
}
