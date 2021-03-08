package com.orsoft.testing_api.repositories;

import com.orsoft.testing_api.domain.Test;

import org.springframework.data.repository.CrudRepository;

public interface ITestRepository extends CrudRepository<Test, Long> {
  
}
