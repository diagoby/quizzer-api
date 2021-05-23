package com.orsoft.quizzer_api.infrastructure.controllers;


import com.orsoft.quizzer_api.domain.contracts.dto.quiz.ReadQuizDTO;
import com.orsoft.quizzer_api.domain.services.quiz.IQuizService;
import com.orsoft.quizzer_api.domain.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping(path = "/users")
public class UserController {
  @Autowired
  IUserService userService;

  @Autowired
  private IQuizService quizService;

  @GetMapping("{id}/quizzes")
  public Set<ReadQuizDTO> getUserQuizzes(@PathVariable("id") String userId) {
    return quizService.getUserQuizzes(userId).getOrThrow((error) ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, error)
    );
  }
}
