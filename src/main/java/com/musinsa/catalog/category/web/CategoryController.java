package com.musinsa.catalog.category.web;

import com.musinsa.catalog.category.dto.CreateCategoryReqDto;
import com.musinsa.catalog.category.dto.DeleteCategoryReqDto;
import com.musinsa.catalog.category.dto.UpdateCategoryReqDto;
import com.musinsa.catalog.category.service.CategoryService;
import com.musinsa.catalog.config.user.annotation.UserId;
import com.musinsa.catalog.persistence.vo.CategoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @GetMapping
  public List<CategoryVO> getCategory() {
    List<CategoryVO> list = categoryService.getCategories();
    return list;
  }

  @PostMapping("create")
  public void postCategory(@UserId String userId, @RequestBody CreateCategoryReqDto request) {
    categoryService.create(request, userId);
  }

  @PostMapping("update/{id}")
  public void postCategoryUpdate(@PathVariable long id, @UserId String userId, @RequestBody UpdateCategoryReqDto request) {
    categoryService.update(id, request, userId);
  }

  @PostMapping("delete")
  public void deleteCategory(@UserId String userId, @RequestBody DeleteCategoryReqDto request) {
    categoryService.delete(request.ids(), userId);
  }
}
