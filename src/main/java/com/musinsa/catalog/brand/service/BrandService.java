package com.musinsa.catalog.brand.service;

import com.musinsa.catalog.brand.dto.CreateBrandReqDto;
import com.musinsa.catalog.brand.dto.UpdateBrandReqDto;
import com.musinsa.catalog.common.code.YnType;
import com.musinsa.catalog.config.cache.CacheType;
import com.musinsa.catalog.config.cache.annotation.EvictCachesByType;
import com.musinsa.catalog.persistence.entity.BrandEntity;
import com.musinsa.catalog.persistence.repository.BrandRepository;
import com.musinsa.catalog.persistence.repository.ItemRepository;
import com.musinsa.catalog.persistence.vo.BrandVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.musinsa.catalog.config.cache.CacheName.BRAND_BY_ID_CACHE;
import static com.musinsa.catalog.config.cache.CacheName.BRAND_LIST_CACHE;

@Service
@RequiredArgsConstructor
public class BrandService {
  private final BrandRepository brandRepository;
  private final ItemRepository itemRepository;

  @Transactional
  @EvictCachesByType(CacheType.BRAND)
  public long create(CreateBrandReqDto request, String userId) {
    BrandEntity brandEntity = BrandEntity.builder()
        .name(request.name())
        .createdId(userId)
        .build();
    brandRepository.save(brandEntity);
    return brandEntity.getId();
  }

  @Cacheable(value = BRAND_LIST_CACHE, unless = "#result == null or #result.isEmpty()")
  public List<BrandVO> getBrands() {
    return brandRepository.findAllByDeleteYn(YnType.N);
  }

  @Transactional
  @EvictCachesByType(CacheType.BRAND)
  public void update(long id, UpdateBrandReqDto request, String userId) {
    brandRepository.updateBrandName(id, request.name(), userId);
    itemRepository.updateBrandName(id, request.name());
  }

  @Transactional
  @EvictCachesByType(CacheType.BRAND)
  public void delete(List<Long> ids, String userId) {
    brandRepository.deleteAllById(ids, userId);
  }


  @Cacheable(value = BRAND_BY_ID_CACHE, key = "#id", unless = "#result == null")
  public BrandEntity getBrand(long id) {
    return brandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Brand Id: " + id));
  }
}
