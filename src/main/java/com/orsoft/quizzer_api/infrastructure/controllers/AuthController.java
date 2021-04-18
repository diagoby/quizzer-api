package com.orsoft.quizzer_api.infrastructure.controllers;


import com.orsoft.quizzer_api.domain.contracts.dto.LoginUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.ReadUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.RegisterUserDTO;
import com.orsoft.quizzer_api.domain.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class AuthController {
  @Autowired
  IUserService userService;

  @PostMapping(path = "register")
  @ResponseStatus(HttpStatus.CREATED)
  public void register(@Valid @RequestBody RegisterUserDTO userDto) {
    userService.register(userDto)
      .ifPresent((error) -> {
        throw new ResponseStatusException(
          HttpStatus.CONFLICT, error.getMessage()
        );
      });
  }

  @PostMapping(path = "login")
  public ReadUserDTO login(@Valid @RequestBody LoginUserDTO loginDto) {
    return userService.login(loginDto)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
  }
}
