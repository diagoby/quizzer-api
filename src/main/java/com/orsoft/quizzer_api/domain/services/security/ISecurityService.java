package com.orsoft.quizzer_api.domain.services.security;

public interface ISecurityService {
  String encodePassword(String password);
  boolean matchPasswordHash(String password, String hash);
}
