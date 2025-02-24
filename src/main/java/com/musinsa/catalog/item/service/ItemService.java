package com.musinsa.catalog.item.service;

import com.musinsa.catalog.brand.service.BrandService;
import com.musinsa.catalog.category.service.CategoryService;
import com.musinsa.catalog.common.code.YnType;
import com.musinsa.catalog.item.dto.CreateItemReqDto;
import com.musinsa.catalog.item.dto.UpdateItemReqDto;
import com.musinsa.catalog.persistence.entity.BrandEntity;
import com.musinsa.catalog.persistence.entity.CategoryEntity;
import com.musinsa.catalog.persistence.entity.ItemEntity;
import com.musinsa.catalog.persistence.repository.ItemRepository;
import com.musinsa.catalog.persistence.vo.ItemVO;
import com.musinsa.catalog.persistence.vo.LowestItemByCategoryVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
  private final BrandService brandService;
  private final CategoryService categoryService;

  private final ItemRepository itemRepository;

  public List<ItemVO> getItem() {
    return itemRepository.findAllByDeleteYn(YnType.N);
  }

  public List<LowestItemByCategoryVO> findLowestItems() {
    return itemRepository.findLowestItemsByCategory();
  }

  public List<LowestItemByCategoryVO> findLowestItemsByBrand() {
    return itemRepository.findLowestItemsByBrand();
  }

  public List<LowestItemByCategoryVO> findMinMaxItemsByBrand(String categoryCode) {
    return itemRepository.findMinMaxByCategory(categoryCode);
  }

  @Transactional
  public void create(CreateItemReqDto request, String userId) {
    BrandEntity brand = brandService.getBrand(request.brandId());
    CategoryEntity category = categoryService.getCategory(request.categoryCode());

    ItemEntity itemEntity = ItemEntity.builder()
        .name(request.name())
        .brandId(brand.getId())
        .brandName(brand.getName())
        .categoryCode(category.getCode())
        .categoryName(category.getName())
        .price(request.price())
        .createdId(userId)
        .build();
    itemRepository.save(itemEntity);
  }

  @Transactional
  public void update(long id, UpdateItemReqDto request, String userId) {
    ItemEntity itemEntity = itemRepository.findById(id).orElse(null);
    if (itemEntity == null) {
      throw new IllegalArgumentException("Item not found: " + id);
    }

    if (request.brandId() != itemEntity.getBrandId()) {
      BrandEntity brand = brandService.getBrand(request.brandId());
      itemEntity.setBrandId(brand.getId());
      itemEntity.setBrandName(brand.getName());
    }

    if (!itemEntity.getCategoryCode().equals(request.categoryCode())) {
      CategoryEntity category = categoryService.getCategory(request.categoryCode());
      itemEntity.setCategoryCode(category.getCode());
      itemEntity.setCategoryName(category.getName());
    }

    itemEntity.setName(request.name());
    itemEntity.setPrice(request.price());
    itemEntity.setUpdatedId(userId);
  }

  public void delete(List<Long> ids) {
    itemRepository.deleteAllById(ids);
  }
}
