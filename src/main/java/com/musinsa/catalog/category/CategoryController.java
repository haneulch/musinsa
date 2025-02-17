package com.musinsa.catalog.category;

import com.musinsa.catalog.brand.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("category")
@RestController
@RequiredArgsConstructor
public class CategoryController {
  private final BrandService brandService;

  @GetMapping("lowest")
  public void getLowestPrice(@RequestParam long id) {

  }

  @GetMapping("lowest/brand")
  public void getLowestBrandPrice(@RequestParam long id) {

  }

  @GetMapping("{name}/lowest")
  public void getLowestPriceByName(@PathVariable String name) {

  }
}
