package com.musinsa.catalog.item.dto;

public record UpdateItemRequest(
    String name,
    long brandId,
    String categoryCode,
    int price
) {
}
