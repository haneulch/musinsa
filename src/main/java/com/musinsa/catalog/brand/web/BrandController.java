package com.musinsa.catalog.brand.web;

import com.musinsa.catalog.brand.dto.BrandDto;
import com.musinsa.catalog.brand.dto.CreateBrandRequest;
import com.musinsa.catalog.brand.dto.DeleteBrandRequest;
import com.musinsa.catalog.brand.dto.UpdateBrandRequest;
import com.musinsa.catalog.brand.service.BrandService;
import com.musinsa.catalog.config.user.annotation.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/api/brand")
@RestController
@RequiredArgsConstructor
public class BrandController {
  private final BrandService brandService;

  @GetMapping
  public List<BrandDto> getBrand() {
    return brandService.getBrands();
  }

  @PostMapping("create")
  public void create(@UserId String userId, @RequestBody CreateBrandRequest request) {
    brandService.create(request, userId);
  }

  @PostMapping("update/{id}")
  public void update(@PathVariable long id, @UserId String userId, @RequestBody UpdateBrandRequest request) {
    brandService.update(id, request, userId);
  }

  @PostMapping("delete")
  public void delete(@UserId String userId, @RequestBody DeleteBrandRequest request) {
    brandService.delete(request.ids(), userId);
  }
}
