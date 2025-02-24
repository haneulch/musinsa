package com.musinsa.catalog.item;

import com.musinsa.catalog.brand.BrandService;
import com.musinsa.catalog.category.CategoryService;
import com.musinsa.catalog.hibernate.entity.BrandEntity;
import com.musinsa.catalog.hibernate.entity.CategoryEntity;
import com.musinsa.catalog.hibernate.entity.ItemEntity;
import com.musinsa.catalog.item.dto.CreateItemRequest;
import com.musinsa.catalog.item.dto.ItemDto;
import com.musinsa.catalog.item.dto.LowestItemByCategoryDto;
import com.musinsa.catalog.item.dto.UpdateItemRequest;
import com.musinsa.catalog.hibernate.repository.ItemRepository;
import com.musinsa.common.Code;
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

  public List<ItemDto> getItem() {
    return itemRepository.findAllByDeleteYn(Code.YnType.N);
  }

  public List<LowestItemByCategoryDto> findLowestItems() {
    return itemRepository.findLowestItemsByCategory();
  }

  public List<LowestItemByCategoryDto> findLowestItemsByBrand() {
    return itemRepository.findLowestItemsByBrand();
  }

  public List<LowestItemByCategoryDto> findMinMaxItemsByBrand(String categoryCode) {
    return itemRepository.findMinMaxByCategory(categoryCode);
  }

  @Transactional
  public void create(CreateItemRequest request, String userId) {
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
  public void update(long id, UpdateItemRequest request, String userId) {
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
