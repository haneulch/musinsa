package com.musinsa.catalog.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YnType {
  Y("Y"), N("N");
  private final String value;
}
