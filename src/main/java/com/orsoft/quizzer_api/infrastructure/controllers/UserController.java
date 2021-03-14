package com.orsoft.quizzer_api.infrastructure.controllers;


import com.orsoft.quizzer_api.domain.contracts.dto.CreateUserDTO;
import com.orsoft.quizzer_api.domain.services.IUserService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {
  @Autowired
  IUserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void register(@Valid @RequestBody CreateUserDTO userDto) {
    userService.register(userDto);
  }
}
