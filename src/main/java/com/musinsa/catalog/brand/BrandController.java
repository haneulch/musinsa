package com.musinsa.catalog.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.musinsa.catalog.brand.dto.BrandCreateDto.BrandCreateRequest;
import static com.musinsa.catalog.brand.dto.BrandUpdateDto.BrandUpdateRequest;

@RequestMapping("brand")
@RestController
@RequiredArgsConstructor
public class BrandController {
  private final BrandService brandService;

  @PostMapping("create")
  public void create(@RequestBody BrandCreateRequest request) {
    System.out.println(request);
  }

  @PostMapping("update/{id}")
  public void update(@PathVariable long id, @RequestBody BrandUpdateRequest item) {
  }

  @PostMapping("delete/{id}")
  public void delete(@PathVariable long id) {
  }
}
