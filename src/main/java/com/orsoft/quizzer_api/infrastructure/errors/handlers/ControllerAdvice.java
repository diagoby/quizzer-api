package com.orsoft.quizzer_api.infrastructure.errors.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {
  @Override
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex,
    HttpHeaders headers,
    HttpStatus status,
    WebRequest request
  ) {
    FieldError fieldError = ex.getFieldError();

    String field = fieldError
      .getField().substring(0, 1).toUpperCase()
      .concat(fieldError.getField().substring(1));

    String message = String.format("%s %s", field, fieldError.getDefaultMessage());

    RestErrorPayload payload = new RestErrorPayload()
      .setPath(((ServletWebRequest)request).getRequest().getServletPath())
      .setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value())
      .setError(status.getReasonPhrase())
      .setMessage(message);

    return ResponseEntity.unprocessableEntity().body(payload);
  }

  @ExceptionHandler(ResponseStatusException.class)
  protected ResponseEntity<Object> handleResponseStatusException(
    ResponseStatusException ex,
    WebRequest request
  ) {
    RestErrorPayload payload = new RestErrorPayload()
      .setPath(((ServletWebRequest)request).getRequest().getServletPath())
      .setError(ex.getStatus().getReasonPhrase())
      .setStatus(ex.getRawStatusCode())
      .setMessage(ex.getMessage());

    return ResponseEntity.status(ex.getStatus()).body(payload);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleAnyException(
    Exception ex,
    WebRequest request
  ) {
    RestErrorPayload payload = new RestErrorPayload()
      .setPath(((ServletWebRequest)request).getRequest().getServletPath())
      .setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
      .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .setMessage(ex.getMessage());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(payload);
  }
}
