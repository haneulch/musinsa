package com.musinsa.catalog.config.cache.annotation;

import com.musinsa.catalog.config.cache.CacheName;
import com.musinsa.catalog.config.cache.CacheType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class EvictCachesAspect {
  private final CacheManager cacheManager;

  @AfterReturning("@annotation(evictCachesByType)")
  public void evictCaches(JoinPoint joinPoint, EvictCachesByType evictCachesByType) {
    CacheType cacheType = evictCachesByType.value();
    List<CacheName> cacheNames = CacheName.getCacheNames(cacheType);

    for (CacheName cacheName : cacheNames) {
      Cache cache = cacheManager.getCache(cacheName.name());
      if (cache != null) {
        cache.clear();
      }
    }

    log.info("Cache Cleared. (type: {}, caches: {})", cacheType, cacheNames);
  }
}
