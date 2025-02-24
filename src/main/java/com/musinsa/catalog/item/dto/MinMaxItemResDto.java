package com.musinsa.catalog.item.dto;

import com.musinsa.catalog.persistence.vo.LowestItemByCategoryVO;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public class MinMaxItemResDto {
  private final List<ItemElement> lowestItems;
  private final List<ItemElement> highestItems;

  public MinMaxItemResDto(List<LowestItemByCategoryVO> list) {
    this.lowestItems = new ArrayList<>();
    this.highestItems = new ArrayList<>();

    if (ObjectUtils.isEmpty(list)) {
      return;
    }

    list.sort(Comparator.comparingInt(LowestItemByCategoryVO::price));

    int minPrice = list.get(0).price();
    list.forEach(item -> {

      ItemElement elem = ItemElement.builder()
          .id(item.id())
          .brandName(item.brandName())
          .categoryName(item.categoryName())
          .price(item.price())
          .build();

      if (item.price() <= minPrice) {
        this.lowestItems.add(elem);
      } else {
        this.highestItems.add(elem);
      }
    });
  }
}
