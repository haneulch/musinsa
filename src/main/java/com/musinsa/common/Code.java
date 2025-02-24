package com.musinsa.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Code {
  @Getter
  @AllArgsConstructor
  public enum YnType {
    Y("Y"), N("N");
    private final String value;
  }
}
