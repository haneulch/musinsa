package com.musinsa.catalog.brand.dto;

import lombok.Getter;

public class BrandUpdateDto {
  
  @Getter
  public static class BrandUpdateRequest {
    private String name;
    private String updatedId;
  }
}
