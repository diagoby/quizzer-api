package com.orsoft.quizzer_api.infrastructure.errors.handlers;

import java.util.Date;

public class RestErrorPayload {
  private int status = 999;
  private Date timestamp = new Date();
  private String error = "";
  private String message = "";
  private String path = "";

  public int getStatus() {
    return status;
  }

  public RestErrorPayload setStatus(int status) {
    this.status = status;
    return this;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public RestErrorPayload setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  public String getError() {
    return error;
  }

  public RestErrorPayload setError(String error) {
    this.error = error;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public RestErrorPayload setMessage(String message) {
    this.message = message;
    return this;
  }

  public String getPath() {
    return path;
  }

  public RestErrorPayload setPath(String path) {
    this.path = path;
    return this;
  }
}
