package com.musinsa.catalog.common.exception;

import com.musinsa.catalog.common.code.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
  private String code;
  private String message;

  public ErrorResponse(ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.message = errorCode.getDescription();
  }

  public ErrorResponse(ErrorCode errorCode, Throwable cause) {
    this.code = errorCode.getCode();
    this.message = cause != null ? cause.getMessage() : null;
  }
}
