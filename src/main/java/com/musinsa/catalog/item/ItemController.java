package com.musinsa.catalog.item;

import com.musinsa.catalog.config.annotation.UserId;
import com.musinsa.catalog.item.dto.CreateItemRequest;
import com.musinsa.catalog.item.dto.DeleteItemRequest;
import com.musinsa.catalog.item.dto.ItemDto;
import com.musinsa.catalog.item.dto.LowestItemByCategoryDto;
import com.musinsa.catalog.item.dto.LowestItemByCategoryResponse;
import com.musinsa.catalog.item.dto.UpdateItemRequest;
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
  public List<ItemDto> item() {
    return itemService.getItem();
  }

  @GetMapping("lowest/category")
  public LowestItemByCategoryResponse lowestCategory() {
    List<LowestItemByCategoryDto> result = itemService.findLowestItems();
    return new LowestItemByCategoryResponse(result);
  }

  @GetMapping("lowest/brand")
  public LowestItemByCategoryResponse lowestBrand() {
    List<LowestItemByCategoryDto> result = itemService.findLowestItemsByBrand();
    return new LowestItemByCategoryResponse(result);
  }

  @GetMapping("minmax/category/{categoryCode}")
  public LowestItemByCategoryResponse minmaxCategory(@PathVariable String categoryCode) {
    List<LowestItemByCategoryDto> result = itemService.findMinMaxItemsByBrand(categoryCode);
    return new LowestItemByCategoryResponse(result);
  }

  @PostMapping("create")
  public void create(@UserId String userId, @RequestBody CreateItemRequest request) {
    itemService.create(request, userId);
  }

  @PostMapping("update/{id}")
  public void update(@PathVariable long id, @UserId String userId, @RequestBody UpdateItemRequest request) {
    itemService.update(id, request, userId);
  }

  @PostMapping("delete")
  public void delete(@RequestBody DeleteItemRequest request) {
    itemService.delete(request.ids());
  }
}
