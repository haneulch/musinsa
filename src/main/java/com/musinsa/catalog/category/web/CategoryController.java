package com.musinsa.catalog.category.web;

import com.musinsa.catalog.category.dto.CategoryDto;
import com.musinsa.catalog.category.dto.CreateCategoryRequest;
import com.musinsa.catalog.category.dto.DeleteCategoryRequest;
import com.musinsa.catalog.category.dto.UpdateCategoryRequest;
import com.musinsa.catalog.category.service.CategoryService;
import com.musinsa.catalog.config.user.annotation.UserId;
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
  public List<CategoryDto> getCategory() {
    List<CategoryDto> list = categoryService.getCategories();
    return list;
  }

  @PostMapping("create")
  public void postCategory(@UserId String userId, @RequestBody CreateCategoryRequest request) {
    categoryService.create(request, userId);
  }

  @PostMapping("update/{id}")
  public void postCategoryUpdate(@PathVariable long id, @UserId String userId, @RequestBody UpdateCategoryRequest request) {
    categoryService.update(id, request, userId);
  }

  @PostMapping("delete")
  public void deleteCategory(@UserId String userId, @RequestBody DeleteCategoryRequest request) {
    categoryService.delete(request.ids(), userId);
  }
}
