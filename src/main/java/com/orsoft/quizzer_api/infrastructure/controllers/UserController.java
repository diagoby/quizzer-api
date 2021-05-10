package com.orsoft.quizzer_api.infrastructure.controllers;


import com.orsoft.quizzer_api.domain.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {
  @Autowired
  IUserService userService;

  // TODO: Implement
}
