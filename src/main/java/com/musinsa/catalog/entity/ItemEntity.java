package com.musinsa.catalog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity(name = "Item")
public class ItemEntity {
  @Id
  private long id;

  private String name;

  private long brandId;

  private String brandName;

  private String categoryCode;

  private String categoryName;

  private int price;

  String createdId;

  String updatedId;

  LocalDateTime createdAt;

  LocalDateTime updatedAt;
}
