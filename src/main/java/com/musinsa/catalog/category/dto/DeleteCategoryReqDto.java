package com.musinsa.catalog.category.dto;

import com.musinsa.catalog.config.user.annotation.UserId;

import java.util.List;

public record DeleteCategoryReqDto(
    List<Long> ids,
    @UserId String updatedId
) {
}
