package com.musinsa.catalog.persistence.repository;

import com.musinsa.catalog.common.code.YnType;
import com.musinsa.catalog.persistence.entity.CategoryEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@DataJpaTest
@TestConstructor(autowireMode = AutowireMode.ALL)
@RequiredArgsConstructor
class CategoryRepositoryTests {

  private final CategoryRepository categoryRepository;
  private final EntityManager entityManager;

  private CategoryEntity createCategory(String code, String name) {
    CategoryEntity category = CategoryEntity.builder().code(code).name(name).build();
    CategoryEntity newCategory = categoryRepository.save(category);

    log.info("Category Created. (id: {})", category.getId());

    return newCategory;
  }

  @DisplayName("카테고리 저장 테스트")
  @Test
  void save() {
    // Given, When
    final String code = "code1";
    final String name = "category1";
    CategoryEntity category = createCategory(code, name);

    // Then
    Optional<CategoryEntity> newCategory = categoryRepository.findById(category.getId());
    assertThat(newCategory).isPresent();
    assertThat(newCategory.get().getCode()).isEqualTo(code);
    assertThat(newCategory.get().getName()).isEqualTo(name);
  }

  @DisplayName("카테고리 코드 중복 오류 테스트")
  @Test
  void duplicate() {
    // Given
    final String code = "code1";
    final String name = "category1";
    CategoryEntity category = createCategory(code, name);

    // When
    Optional<CategoryEntity> newCategory = categoryRepository.findById(category.getId());
    CategoryEntity duplicatedCategory = CategoryEntity.builder().code(code).name(name).build();

    // Then
    assertThat(newCategory).isPresent();
    assertThat(newCategory.get().getCode()).isEqualTo(code);
    assertThatThrownBy(() -> categoryRepository.save(duplicatedCategory)).isInstanceOf(DataIntegrityViolationException.class);
  }

  @DisplayName("카테고리 수정 테스트")
  @Test
  void update() {
    // Given
    final String code = "code2";
    CategoryEntity category = createCategory(code, "category2");

    // When
    final String newName = "newCategory";
    final String updatedId = "system";
    categoryRepository.updateCategoryName(category.getCode(), newName, updatedId);
    entityManager.clear();

    // Then
    Optional<CategoryEntity> checkCategoryUpdated = categoryRepository.findById(category.getId());
    assertThat(checkCategoryUpdated).isPresent();
    assertThat(checkCategoryUpdated.get().getCode()).isEqualTo(code);
    assertThat(checkCategoryUpdated.get().getName()).isEqualTo(newName);
    assertThat(checkCategoryUpdated.get().getUpdatedId()).isEqualTo(updatedId);
  }

  @DisplayName("카테고리 삭제 테스트")
  @Test
  void delete() {
    // Given
    CategoryEntity category = createCategory("code3", "category3");

    // When
    categoryRepository.deleteById(category.getId());

    // Then
    Optional<CategoryEntity> checkCategoryDeleted = categoryRepository.findById(category.getId());
    assertThat(checkCategoryDeleted).isEmpty();
  }

  @DisplayName("카테고리 삭제(deleteYn) 테스트")
  @Test
  void deleteYn() {
    // Given
    CategoryEntity category = createCategory("code3", "category3");

    // When
    final String updatedId = "system";
    categoryRepository.deleteAllById(List.of(category.getId()), updatedId);
    entityManager.clear();

    // Then
    Optional<CategoryEntity> checkCategoryDeleted = categoryRepository.findById(category.getId());
    assertThat(checkCategoryDeleted).isPresent();
    assertThat(checkCategoryDeleted.get().getDeleteYn()).isEqualTo(YnType.Y);
    assertThat(checkCategoryDeleted.get().getUpdatedId()).isEqualTo(updatedId);
  }

  @DisplayName("카테고리 조회 테스트")
  @Test
  void select() {
    // Given
    final String code = "code3";
    createCategory(code, "category3");

    // When
    Optional<CategoryEntity> category1 = categoryRepository.findByCode(code);
    Optional<CategoryEntity> category2 = categoryRepository.findByCode("someCode");

    // Then
    assertThat(category1).isPresent();
    assertThat(category1.get().getCode()).isEqualTo(code);
    assertThat(category2).isEmpty();
  }
}
