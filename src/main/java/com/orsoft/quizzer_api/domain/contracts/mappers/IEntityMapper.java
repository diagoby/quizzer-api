package com.orsoft.quizzer_api.domain.contracts.mappers;

public interface IEntityMapper<E, D1, D2> {
  E toEntity(D1 dto);
  D2 toDTO(E entity);
}
