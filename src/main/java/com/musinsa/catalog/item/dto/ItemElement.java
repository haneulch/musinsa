package com.musinsa.catalog.item.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemElement {
  private long id;
  private String brandName;
  private String categoryName;
  private int price;
}
