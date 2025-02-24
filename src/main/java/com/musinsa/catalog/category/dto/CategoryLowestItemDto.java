package com.musinsa.catalog.category.dto;

import java.util.List;

public class CategoryLowestItemDto {
  public record CategoryLowestItemResponse(
      List<Item> items,
      int totalPrice
  ) {}

  public record Item(
      String categoryCode,
      String categoryName,
      long brandId,
      String brandName,
      long price
  ) {}
}
