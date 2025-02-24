package com.musinsa.catalog.common.exception;

import com.musinsa.catalog.common.code.ErrorCode;

public class CatalogException extends RuntimeException {
  public CatalogException(ErrorCode errorCode) {
    super(errorCode.getCode());
  }
}
