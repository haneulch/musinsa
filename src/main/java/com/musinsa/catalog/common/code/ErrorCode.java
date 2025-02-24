package com.musinsa.catalog.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  BAD_REQUEST("4000", "BAD_REQUEST"),
  UNAUTHORIZED("4010", "UNAUTHORIZED"),
  NOT_FOUND("4040", "NOT_FOUND"),
  INTERNAL_SERVER_ERROR("5000", "INTERNAL_SERVER_ERROR"),
  TOKEN_EXPIRED("5001", "TOKEN_EXPIRED"),
  INVALID_TOKEN("5002", "INVALID_TOKEN"),

  CATEGORY_CANNOT_DUPLICATE("6000", "CATEGORY_CANNOT_DUPLICATE"),

  UNKNOWN("9999", "UNKNOWN"),
  ;

  private static final Map<String, ErrorCode> error = Arrays.stream(values())
      .collect(Collectors.toMap(ErrorCode::getCode, Function.identity()));

  public static ErrorCode get(String code) {
    return error.getOrDefault(code, UNKNOWN);
  }

  private final String code;
  private final String description;
}
