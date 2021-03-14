package com.orsoft.quizzer_api.domain.services;

public interface ISecurityService {
  String encodePassword(String password);
}
