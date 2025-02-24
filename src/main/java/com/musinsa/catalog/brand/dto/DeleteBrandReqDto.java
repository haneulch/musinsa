package com.musinsa.catalog.brand.dto;

import java.util.List;

public record DeleteBrandReqDto(
    List<Long> ids
) {
}
