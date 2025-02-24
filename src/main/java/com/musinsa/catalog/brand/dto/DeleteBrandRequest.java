package com.musinsa.catalog.brand.dto;

import java.util.List;

public record DeleteBrandRequest(
    List<Long> ids
) {
}
