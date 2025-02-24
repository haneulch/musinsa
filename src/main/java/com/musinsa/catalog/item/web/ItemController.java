package com.musinsa.catalog.item.web;

import com.musinsa.catalog.config.user.annotation.UserId;
import com.musinsa.catalog.item.dto.CreateItemReqDto;
import com.musinsa.catalog.item.dto.DeleteItemReqDto;
import com.musinsa.catalog.item.dto.LowestItemByCategoryResDto;
import com.musinsa.catalog.item.dto.MinMaxItemResDto;
import com.musinsa.catalog.item.dto.UpdateItemReqDto;
import com.musinsa.catalog.item.service.ItemService;
import com.musinsa.catalog.persistence.vo.ItemVO;
import com.musinsa.catalog.persistence.vo.LowestItemByCategoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/item")
@RestController
@RequiredArgsConstructor
public class ItemController {
  private final ItemService itemService;

  @GetMapping
  public List<ItemVO> item() {
    return itemService.getItem();
  }

  @GetMapping("lowest/category")
  public LowestItemByCategoryResDto lowestCategory() {
    List<LowestItemByCategoryVO> result = itemService.findLowestItems();
    return new LowestItemByCategoryResDto(result);
  }

  @GetMapping("lowest/brand")
  public LowestItemByCategoryResDto lowestBrand() {
    List<LowestItemByCategoryVO> result = itemService.findLowestItemsByBrand();
    return new LowestItemByCategoryResDto(result);
  }

  @GetMapping("minmax/category/{categoryCode}")
  public MinMaxItemResDto minmaxCategory(@PathVariable String categoryCode) {
    List<LowestItemByCategoryVO> result = itemService.findMinMaxItemsByBrand(categoryCode);
    return new MinMaxItemResDto(result);
  }

  @PostMapping("create")
  public void create(@UserId String userId, @RequestBody CreateItemReqDto request) {
    itemService.create(request, userId);
  }

  @PostMapping("update/{id}")
  public void update(@PathVariable long id, @UserId String userId, @RequestBody UpdateItemReqDto request) {
    itemService.update(id, request, userId);
  }

  @PostMapping("delete")
  public void delete(@RequestBody DeleteItemReqDto request) {
    itemService.delete(request.ids());
  }
}
