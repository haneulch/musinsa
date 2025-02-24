package com.musinsa.catalog.brand;

import static com.musinsa.catalog.config.cache.CacheName.BRAND_BY_ID_CACHE;
import static com.musinsa.catalog.config.cache.CacheName.BRAND_LIST_CACHE;

import com.musinsa.catalog.brand.dto.BrandDto;
import com.musinsa.catalog.brand.dto.CreateBrandRequest;
import com.musinsa.catalog.brand.dto.UpdateBrandRequest;
import com.musinsa.catalog.config.cache.CacheType;
import com.musinsa.catalog.config.cache.annotation.EvictCachesByType;
import com.musinsa.catalog.hibernate.entity.BrandEntity;
import com.musinsa.catalog.hibernate.repository.BrandRepository;
import com.musinsa.common.Code;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
  private final BrandRepository brandRepository;

  @EvictCachesByType(CacheType.BRAND)
  public void create(CreateBrandRequest request, String userId) {
    BrandEntity brandEntity = BrandEntity.builder()
        .name(request.name())
        .createdId(userId)
        .build();
    brandRepository.save(brandEntity);
  }

  @Cacheable(value = BRAND_LIST_CACHE, unless = "#result == null or #result.isEmpty()")
  public List<BrandDto> getBrands() {
    return brandRepository.findAllByDeleteYn(Code.YnType.N);
  }

  @EvictCachesByType(CacheType.BRAND)
  public void update(long id, UpdateBrandRequest request, String userId) {
    brandRepository.updateBrandName(id, request.name(), userId);
  }

  @EvictCachesByType(CacheType.BRAND)
  public void delete(List<Long> ids, String userId) {
    brandRepository.deleteAllById(ids, userId);
  }


  @Cacheable(value = BRAND_BY_ID_CACHE, key = "#id", unless = "#result == null")
  public BrandEntity getBrand(long id) {
    return brandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Brand Id: " + id));
  }
}
