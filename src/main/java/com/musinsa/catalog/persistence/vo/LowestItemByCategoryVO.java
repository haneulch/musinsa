package com.musinsa.catalog.persistence.vo;

public record LowestItemByCategoryVO(
  long id,
  String brandName,
  String categoryName,
  int price
) {
}