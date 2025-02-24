package com.musinsa.catalog.config.cache.annotation;

import com.musinsa.catalog.config.cache.CacheType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EvictCachesByType {
  CacheType value();
}
