package com.orsoft.testing_api.controllers;

import com.orsoft.testing_api.domain.Test;
import com.orsoft.testing_api.repositories.ITestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/tests")
public class TestsController {
  @Autowired
  private ITestRepository testRepository;

  @GetMapping
  public Iterable<Test> getTests() {
    Iterable<Test> tests = testRepository.findAll();

    return tests;
  }
}
