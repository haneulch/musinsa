package com.musinsa.catalog.category.service;

import com.musinsa.catalog.category.dto.CreateCategoryReqDto;
import com.musinsa.catalog.category.dto.UpdateCategoryReqDto;
import com.musinsa.catalog.persistence.entity.CategoryEntity;
import com.musinsa.catalog.persistence.repository.CategoryRepository;
import com.musinsa.catalog.persistence.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTests {

  @InjectMocks
  private CategoryService categoryService;

  @Mock
  private CategoryRepository categoryRepository;

  @Mock
  private ItemRepository itemRepository;

  @Test
  @DisplayName("카테고리 생성 테스트")
  void createCategory() {
    // Given
    final long id = 1L;
    final String code = "code";
    final String name = "name";
    final String userId = "testUser";
    CreateCategoryReqDto request = new CreateCategoryReqDto(code, name);

    when(categoryRepository.save(any(CategoryEntity.class))).thenAnswer(invocation -> {
      CategoryEntity entity = invocation.getArgument(0);
      ReflectionTestUtils.setField(entity, "id", id);
      return entity;
    });

    // When
    long newCategoryId = categoryService.create(request, userId);

    assertThat(newCategoryId).isEqualTo(id);
    verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
  }

  @Test
  @DisplayName("카테고리 수정 테스트")
  void updateCategory() {
    // Given
    String categoryCode = "code";
    String userId = "testUser";
    String newCategoryName = "New Category";

    UpdateCategoryReqDto request = new UpdateCategoryReqDto(newCategoryName);

    // When
    categoryService.update(categoryCode, request, userId);

    // Then
    verify(categoryRepository, times(1)).updateCategoryName(categoryCode, newCategoryName, userId);
    verify(itemRepository, times(1)).updateCategoryName(categoryCode, newCategoryName);
  }

  @Test
  @DisplayName("카테고리 삭제 테스트")
  void deleteCategory() {
    // Given
    final List<Long> categoryIds = List.of(1L, 2L);
    final String userId = "testUser";

    // When
    categoryService.delete(categoryIds, userId);

    // Then
    verify(categoryRepository, times(1)).deleteAllById(categoryIds, userId);
  }
}
