package com.musinsa.catalog.item.dto;

public record LowestItemByCategoryDto(
  long id,
  String brandName,
  String categoryName,
  int price
) {
}