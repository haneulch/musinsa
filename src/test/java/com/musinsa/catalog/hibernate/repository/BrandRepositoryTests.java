package com.musinsa.catalog.hibernate.repository;

import com.musinsa.catalog.common.code.YnType;
import com.musinsa.catalog.persistence.entity.BrandEntity;
import com.musinsa.catalog.persistence.repository.BrandRepository;
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
class BrandRepositoryTests {

  private final BrandRepository brandRepository;
  private final EntityManager entityManager;

  private BrandEntity createBrand(String name) {
    BrandEntity brand = BrandEntity.builder().name(name).build();
    BrandEntity newBrand = brandRepository.save(brand);

    log.info("Brand Created. (id: {})", brand.getId());

    return newBrand;
  }

  @DisplayName("브랜드 저장 테스트")
  @Test
  void save() {
    // Given, When
    final String name = "name";
    BrandEntity brand = createBrand(name);

    // Then
    Optional<BrandEntity> newBrand = brandRepository.findById(brand.getId());
    assertThat(newBrand).isPresent();
    assertThat(newBrand.get().getName()).isEqualTo(name);
  }

  @DisplayName("브랜드 이름 중복 오류 테스트")
  @Test
  void duplicate() {
    // Given
    final String name = "name";
    BrandEntity brand = createBrand(name);

    // When
    Optional<BrandEntity> newBrand = brandRepository.findById(brand.getId());
    BrandEntity duplicatedBrand = BrandEntity.builder().name(name).build();

    // Then
    assertThat(newBrand).isPresent();
    assertThat(newBrand.get().getName()).isEqualTo(name);
    assertThatThrownBy(() ->
        brandRepository.save(duplicatedBrand)).isInstanceOf(DataIntegrityViolationException.class);
  }

  @DisplayName("브랜드 수정 테스트")
  @Test
  void update() {
    // Given
    BrandEntity brand = createBrand("name2");

    // When
    final String newName = "newName";
    final String updatedId = "system";
    brandRepository.updateBrandName(brand.getId(), newName, updatedId);
    entityManager.clear();

    // Then
    Optional<BrandEntity> checkBrandUpdated = brandRepository.findById(brand.getId());
    assertThat(checkBrandUpdated).isPresent();
    assertThat(checkBrandUpdated.get().getName()).isEqualTo(newName);
    assertThat(checkBrandUpdated.get().getUpdatedId()).isEqualTo(updatedId);
  }

  @DisplayName("브랜드 삭제 테스트")
  @Test
  void delete() {
    // Given
    BrandEntity brand = createBrand("name3");

    // When
    brandRepository.deleteById(brand.getId());

    // Then
    Optional<BrandEntity> checkBrandDeleted = brandRepository.findById(brand.getId());
    assertThat(checkBrandDeleted).isEmpty();
  }

  @DisplayName("브랜드 삭제(deleteYn) 테스트")
  @Test
  void deleteYn() {
    // Given
    BrandEntity brand = createBrand("name3");

    // When
    final String updatedId = "system";
    brandRepository.deleteAllById(List.of(brand.getId()), updatedId);
    entityManager.clear();

    // Then
    Optional<BrandEntity> checkBrandDeleted = brandRepository.findById(brand.getId());
    assertThat(checkBrandDeleted).isPresent();
    assertThat(checkBrandDeleted.get().getDeleteYn()).isEqualTo(YnType.Y);
    assertThat(checkBrandDeleted.get().getUpdatedId()).isEqualTo(updatedId);
  }
}