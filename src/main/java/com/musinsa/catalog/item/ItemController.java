package com.musinsa.catalog.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("item")
@RestController
@RequiredArgsConstructor
public class ItemController {
  private final ItemService itemService;

  @PostMapping("create")
  public void create(@RequestBody Object item) {
  }

  @PostMapping("update/{id}")
  public void update(@PathVariable long id, @RequestBody Object item) {
  }

  @PostMapping("delete/{id}")
  public void delete(@PathVariable long id) {
  }
}
