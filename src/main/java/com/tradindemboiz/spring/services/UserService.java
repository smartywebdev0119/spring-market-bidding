package com.tradindemboiz.spring.services;

import com.tradindemboiz.spring.entities.User;
import com.tradindemboiz.spring.models.LoginDto;
import com.tradindemboiz.spring.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

//  public User registerUser(User user) {
//    return myUserDetailsService.addUser(user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
//  }

  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  public User loginUser(LoginDto user) {
    User dbUser = findByUsername(user.getUsername());
    if (dbUser == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong credentials.");
    }

    if (myUserDetailsService.getEncoder().matches(user.getPassword(), dbUser.getPassword())) {
      return dbUser;
    }
    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong credentials.");
  }

  public User registerUser(User user) {
    User newUser = new User(user.getUsername(), user.getEmail(), myUserDetailsService.getEncoder().encode(user.getPassword()), user.getRoles());
    return userRepo.save(newUser);
  }

}
