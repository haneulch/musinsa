package com.musinsa.catalog.repository;

import com.musinsa.catalog.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
