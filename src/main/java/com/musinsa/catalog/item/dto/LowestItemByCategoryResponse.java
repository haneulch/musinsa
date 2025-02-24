package com.musinsa.catalog.item.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LowestItemByCategoryResponse {
  private final List<ItemElement> items;
  private int totalPrice;

  @Getter
  @Builder
  public static class ItemElement {
    private long id;
    private String brandName;
    private String categoryName;
    private int price;
  }

  public LowestItemByCategoryResponse(List<LowestItemByCategoryDto> list) {
    this.items = new ArrayList<>();

    list.forEach(item -> {
      this.items.add(
          ItemElement.builder()
            .id(item.id())
            .brandName(item.brandName())
            .categoryName(item.categoryName())
            .price(item.price())
            .build()
      );
      this.totalPrice += item.price();
    });
  }
}
