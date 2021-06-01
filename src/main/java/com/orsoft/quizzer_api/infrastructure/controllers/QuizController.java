package com.orsoft.quizzer_api.infrastructure.controllers;

import com.orsoft.quizzer_api.domain.contracts.dto.attempt.CreateAttemptDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.attempt.result.AttemptResultDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.quiz.CreateQuizDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.quiz.ReadQuizDTO;
import com.orsoft.quizzer_api.domain.services.quiz.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createQuiz(@Valid @RequestBody CreateQuizDTO quizDto) {
    quizService.createQuiz(quizDto).ifError((error) -> {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error);
    });
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public void removeQuiz(@PathVariable("id") String quizId) {
    quizService.removeQuiz(quizId).ifError((error) -> {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, error);
    });
  }

  @PostMapping("{id}/attempts")
  @ResponseStatus(HttpStatus.CREATED)
  public AttemptResultDTO makeQuizAttempt(
    @PathVariable("id") String quizId,
    @Valid @RequestBody CreateAttemptDTO attemptDto
  ) {
    return quizService.makeQuizAttempt(quizId, attemptDto).getOrThrow((error) ->
      new ResponseStatusException(HttpStatus.BAD_REQUEST, error)
    );
  }
}
