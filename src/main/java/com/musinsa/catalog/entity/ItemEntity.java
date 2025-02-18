package com.musinsa.catalog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ITEM")
public class ItemEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private long brandId;

  private String brandName;

  private String categoryCode;

  private String categoryName;

  private int price;

  String createdId;

  String updatedId;

  LocalDateTime createdAt;

  LocalDateTime updatedAt = LocalDateTime.now();
}
