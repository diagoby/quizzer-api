package com.orsoft.quizzer_api.infrastructure.controllers;

import com.orsoft.quizzer_api.domain.contracts.dto.quiz.ReadQuizDTO;
import com.orsoft.quizzer_api.domain.services.quiz.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping(path = "/quizzes")
public class QuizController {
  @Autowired
  private IQuizService quizService;

  @GetMapping("{id}")
  public ReadQuizDTO getQuizById(@PathVariable("id") String quizId) {
    return quizService.getQuizById(quizId).getOrThrow((error) ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, error)
    );
  }
}
