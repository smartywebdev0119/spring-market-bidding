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

  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

}
