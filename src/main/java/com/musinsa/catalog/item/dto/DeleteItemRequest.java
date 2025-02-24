package com.musinsa.catalog.item.dto;

import com.musinsa.catalog.config.annotation.UserId;

import java.util.List;

public record DeleteItemRequest(
    List<Long> ids,
    @UserId String updatedId
) {
}
