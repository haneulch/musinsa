package com.musinsa.catalog.category.service;

import com.musinsa.catalog.category.dto.CategoryDto;
import com.musinsa.catalog.category.dto.CreateCategoryRequest;
import com.musinsa.catalog.category.dto.UpdateCategoryRequest;
import com.musinsa.catalog.common.code.YnType;
import com.musinsa.catalog.config.cache.CacheType;
import com.musinsa.catalog.config.cache.annotation.EvictCachesByType;
import com.musinsa.catalog.persistence.entity.CategoryEntity;
import com.musinsa.catalog.persistence.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.musinsa.catalog.config.cache.CacheName.CATEGORY_BY_CODE_CACHE;
import static com.musinsa.catalog.config.cache.CacheName.CATEGORY_LIST_CACHE;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;

  @Cacheable(value = CATEGORY_LIST_CACHE, unless = "#result == null or #result.isEmpty()")
  public List<CategoryDto> getCategories() {
    return categoryRepository.findAllByDeleteYn(YnType.N);
  }

  @Transactional
  @EvictCachesByType(CacheType.CATEGORY)
  public void create(CreateCategoryRequest request, String userId) {
    CategoryEntity newCategory = CategoryEntity.builder()
        .code(request.code())
        .name(request.name())
        .createdId(userId)
        .build();
    categoryRepository.save(newCategory);
  }

  @Transactional
  @EvictCachesByType(CacheType.CATEGORY)
  public void update(long id, UpdateCategoryRequest updateCategoryDto, String userId) {
    categoryRepository.updateCategoryName(id, updateCategoryDto.name(), userId);
  }

  @Transactional
  @EvictCachesByType(CacheType.CATEGORY)
  public void delete(List<Long> ids, String userId) {
    categoryRepository.deleteAllById(ids, userId);
  }

  @Cacheable(value = CATEGORY_BY_CODE_CACHE, key = "#code", unless = "#result == null")
  public CategoryEntity getCategory(String code) {
    return categoryRepository.findByCode(code).orElseThrow(() -> new IllegalArgumentException("Invalid Category Code: " + code));
  }
}
