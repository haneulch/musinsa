package com.musinsa.catalog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity(name = "Category")
public class CategoryEntity {
  @Id
  private long id;

  private String code;

  private String name;

  String createdId;

  String updatedId;

  LocalDateTime createdAt;

  LocalDateTime updatedAt;
}
