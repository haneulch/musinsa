package com.musinsa.catalog.brand.web;

import com.musinsa.catalog.brand.dto.CreateBrandReqDto;
import com.musinsa.catalog.brand.dto.DeleteBrandReqDto;
import com.musinsa.catalog.brand.dto.UpdateBrandReqDto;
import com.musinsa.catalog.brand.service.BrandService;
import com.musinsa.catalog.config.user.annotation.UserId;
import com.musinsa.catalog.persistence.vo.BrandVO;
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
  public List<BrandVO> getBrand() {
    return brandService.getBrands();
  }

  @PostMapping("create")
  public void create(@UserId String userId, @RequestBody CreateBrandReqDto request) {
    brandService.create(request, userId);
  }

  @PostMapping("update/{id}")
  public void update(@PathVariable long id, @UserId String userId, @RequestBody UpdateBrandReqDto request) {
    brandService.update(id, request, userId);
  }

  @PostMapping("delete")
  public void delete(@UserId String userId, @RequestBody DeleteBrandReqDto request) {
    brandService.delete(request.ids(), userId);
  }
}
