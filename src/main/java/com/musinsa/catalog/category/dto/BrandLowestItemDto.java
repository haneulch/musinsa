package com.musinsa.catalog.category.dto;

public class BrandLowestItemDto {

  public record BrandLowestItemResponse(
      String categoryCode,
      String categoryName,
      long brandId,
      String brandName,
      long price
  ){}
}
