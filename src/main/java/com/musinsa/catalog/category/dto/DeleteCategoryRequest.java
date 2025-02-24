package com.musinsa.catalog.category.dto;

import com.musinsa.catalog.config.annotation.UserId;

import java.util.List;

public record DeleteCategoryRequest(
    List<Long> ids,
    @UserId String updatedId
) {
}
