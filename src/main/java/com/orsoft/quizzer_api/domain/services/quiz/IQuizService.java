package com.orsoft.quizzer_api.domain.services.quiz;

import com.orsoft.quizzer_api.domain.contracts.dto.attempt.CreateAttemptDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.quiz.CreateQuizDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.quiz.ReadQuizDTO;
import com.orsoft.quizzer_api.domain.utils.Result;

import java.util.Set;

public interface IQuizService {
  Result<ReadQuizDTO, String> getQuizById(String id);
  Result<Set<ReadQuizDTO>, String> getUserQuizzes(String userId);
  Result<Object, String> createQuiz(CreateQuizDTO quizDto);
  Result<Object, String> makeQuizAttempt(String quizId, CreateAttemptDTO attemptDto);
}
