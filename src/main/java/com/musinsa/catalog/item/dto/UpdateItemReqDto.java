package com.musinsa.catalog.item.dto;

public record UpdateItemReqDto(
    String name,
    long brandId,
    String categoryCode,
    int price
) {
}
