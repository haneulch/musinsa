package com.musinsa.catalog.item.dto;

import com.musinsa.catalog.config.user.annotation.UserId;

import java.util.List;

public record DeleteItemReqDto(
    List<Long> ids,
    @UserId String updatedId
) {
}
