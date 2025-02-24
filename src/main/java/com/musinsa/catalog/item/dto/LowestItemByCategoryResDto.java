package com.musinsa.catalog.item.dto;

import com.musinsa.catalog.persistence.vo.LowestItemByCategoryVO;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LowestItemByCategoryResDto {
  private final List<ItemElement> items;
  private int totalPrice;

  public LowestItemByCategoryResDto(List<LowestItemByCategoryVO> list) {
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
