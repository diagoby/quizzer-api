package com.orsoft.quizzer_api.domain.utils;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Result<V, E> {
  private Optional<V> value;
  private Optional<E> error;

  private Result(Optional<V> value, Optional<E> error) {
    this.value = value;
    this.error = error;
  }

  public static <V, E> Result<V, E> ok(V value) {
    return new Result<>(Optional.of(value), Optional.empty());
  }

  public static <V, E> Result<V, E> error(E error) {
    return new Result<>(Optional.empty(), Optional.of(error));
  }

  public static <V, E> Result<V, E> empty() {
    return new Result<>(Optional.empty(), Optional.empty());
  }

  public static <V, E> Result<V, E> ofOptional(Optional<V> value, E error) {
    return value.map(Result::<V, E>ok).orElse(error(error));
  }

  public <U> Result<U, E> flatMapValue(Function<? super V, Result<U, E>> mapper) {
    return value.map(mapper).orElse(error(getError()));
  }

  public <T> Result<V, T> flatMapError(Function<? super E, Result<V, T>> mapper) {
    return error.map(mapper).orElse(ok(getValue()));
  }

  public <U> Result<U, E> mapValue(Function<? super V, ? extends U> mapper) {
    return new Result<>(value.map(mapper), error);
  }

  public <T> Result<V, T> mapError(Function<? super E, ? extends T> mapper) {
    return new Result<>(value, error.map(mapper));
  }

  public <X extends Throwable> V getOrThrow(Function<E, ? extends X> action) throws X {
    return value.orElseThrow(() -> action.apply(getError()));
  }

  public void ifValue(Consumer<? super V> action) {
    if(isValue()) {
      action.accept(getValue());
    }
  }

  public void ifError(Consumer<? super E> action) {
    if(isError()) {
      action.accept(getError());
    }
  }

  public boolean isValue() {
    return value.isPresent();
  }

  public boolean isError() {
    return error.isPresent();
  }

  public V getValue() {
    return value.orElse(null);
  }

  public E getError() {
    return error.orElse(null);
  }
}
