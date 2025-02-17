package com.musinsa.catalog.category.dto;

import lombok.Getter;

public class BrandLowestItemDto {

  @Getter
  public static class BrandLowestItemResponse {
    private String categoryCode;
    private String categoryName;
    private long brandId;
    private String brandName;
    private long price;
  }
}
