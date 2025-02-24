package com.musinsa.catalog.item.dto;

public record ItemDto(
    long id,
    String name,
    long brandId,
    String brandName,
    String categoryCode,
    String categoryName,
    int price
) {
}
