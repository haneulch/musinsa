package com.musinsa.catalog.persistence.vo;

public record ItemVO(
    long id,
    String name,
    long brandId,
    String brandName,
    String categoryCode,
    String categoryName,
    int price
) {
}
