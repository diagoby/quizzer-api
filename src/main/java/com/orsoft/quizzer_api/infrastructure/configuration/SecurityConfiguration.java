package com.orsoft.quizzer_api.infrastructure.configuration;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration {
  @Value("${hash.workfactor}")
  private int hashWorkFactor;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(hashWorkFactor, new SecureRandom());
  }
}
