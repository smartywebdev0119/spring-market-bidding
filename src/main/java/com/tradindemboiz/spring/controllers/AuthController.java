package com.tradindemboiz.spring.controllers;

import com.tradindemboiz.spring.entities.User;
import com.tradindemboiz.spring.models.LoginDto;
import com.tradindemboiz.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  UserService userService;

  @GetMapping("/whoami")
  public ResponseEntity<User> whoami(){
    System.out.println("JAG EEEEEEEEEEEEEEER HÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄR");
    User user = userService.getCurrentUser();
    System.out.println(user.getUsername());
    return ResponseEntity.ok(user);
  }

//  @PostMapping("/login")
//  public ResponseEntity<User> loginUser(@RequestBody LoginDto userToLogin){
//    User user = userService.loginUser(userToLogin);
//    return ResponseEntity.ok(user);
//  }
}
