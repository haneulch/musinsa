package com.musinsa.catalog.item.dto;

public record CreateItemReqDto(
    String name,
    long brandId,
    String categoryCode,
    int price
) {
}
