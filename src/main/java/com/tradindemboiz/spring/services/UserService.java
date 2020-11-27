package com.tradindemboiz.spring.services;

import com.tradindemboiz.spring.entities.User;
import com.tradindemboiz.spring.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  UserRepo userRepo;

  @Autowired
  MyUserDetailsService myUserDetailsService;

  public User getCurrentUser() {
    String username = myUserDetailsService.getCurrentUser();
    return userRepo.findByUsername(username);
  }

  public User findByUsername(String username) {
    return userRepo.findByUsername(username);
  }


  public List<User> getAllUsers() {
    return userRepo.findAll();
  }


  public User registerUser(User user) {
    User newUser = new User(user.getUsername(), user.getEmail(), myUserDetailsService.getEncoder().encode(user.getPassword()), user.getRoles());
    return userRepo.save(newUser);
  }

}
