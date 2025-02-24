package com.musinsa.catalog.config.cache;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum CacheName {

  CATEGORY_LIST(CacheType.CATEGORY),
  CATEGORY_BY_CODE(CacheType.CATEGORY),

  BRAND_LIST(CacheType.BRAND),
  BRAND_BY_ID(CacheType.BRAND),
  ;

  private final CacheType type;

  private static final Map<CacheType, List<CacheName>> CACHE_TYPE_MAP = Arrays.stream(values())
      .collect(Collectors.groupingBy(CacheName::getType, Collectors.toList()));

  public static List<CacheName> getCacheNames(CacheType type) {
    return CACHE_TYPE_MAP.getOrDefault(type, Collections.emptyList());
  }

  public static final String CATEGORY_LIST_CACHE = "CATEGORY_LIST";
  public static final String CATEGORY_BY_CODE_CACHE = "CATEGORY_BY_CODE";

  public static final String BRAND_LIST_CACHE = "BRAND_LIST";
  public static final String BRAND_BY_ID_CACHE = "BRAND_BY_ID";
}
