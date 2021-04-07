package com.orsoft.quizzer_api.domain.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements ISecurityService {

  private PasswordEncoder passwordEncoder;

  @Autowired
  public SecurityService(final PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public String encodePassword(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }

  @Override
  public boolean matchPasswordHash(String password, String hash) {
    return passwordEncoder.matches(password, hash);
  }
}
