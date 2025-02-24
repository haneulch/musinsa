package com.musinsa.catalog.brand.service;

import com.musinsa.catalog.brand.dto.UpdateBrandReqDto;
import com.musinsa.catalog.persistence.repository.BrandRepository;
import com.musinsa.catalog.persistence.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BrandServiceTests {

  @InjectMocks
  private BrandService brandService;

  @Mock
  private BrandRepository brandRepository;

  @Mock
  private ItemRepository itemRepository;

  @Test
  @DisplayName("브랜드 업데이트 테스트")
  void updateBrand() {
    // Given
    long brandId = 1L;
    String userId = "testUser";
    String newBrandName = "New Brand";

    UpdateBrandReqDto request = new UpdateBrandReqDto(newBrandName);

    // When
    brandService.update(brandId, request, userId);

    // Then
    verify(brandRepository, times(1)).updateBrandName(brandId, newBrandName, userId);
    verify(itemRepository, times(1)).updateBrandName(brandId, newBrandName);
  }
}
