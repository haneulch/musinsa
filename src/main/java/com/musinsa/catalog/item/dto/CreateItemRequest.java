package com.musinsa.catalog.item.dto;

public record CreateItemRequest(
    String name,
    long brandId,
    String categoryCode,
    int price
) {
}
