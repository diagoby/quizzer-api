package com.orsoft.quizzer_api.domain.services.quiz;

import com.orsoft.quizzer_api.domain.contracts.dto.quiz.ReadQuizDTO;
import com.orsoft.quizzer_api.domain.utils.Result;

public interface IQuizService {
  Result<ReadQuizDTO, String> getQuizById(String id);
}
