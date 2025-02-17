package com.musinsa.catalog.category.dto;

import lombok.Getter;

import java.util.List;

public class CategoryLowestItemDto {

  @Getter
  public static class CategoryLowestItemResponse {
    private List<Item> items;
    private int totalPrice;

    static class Item {
      private String categoryCode;
      private String categoryName;
      private long brandId;
      private String brandName;
      private long price;
    }
  }
}
