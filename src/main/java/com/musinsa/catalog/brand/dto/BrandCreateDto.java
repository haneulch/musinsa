package com.musinsa.catalog.brand.dto;

import lombok.Getter;
import lombok.ToString;

public class BrandCreateDto {

  @Getter
  @ToString
  public static class BrandCreateRequest {
    private String name;
    private String createdId;
  }

  @Getter
  public static class BrandCreateResponse {
    private long id;
  }
}
