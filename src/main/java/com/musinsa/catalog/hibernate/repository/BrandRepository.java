package com.musinsa.catalog.hibernate.repository;

import com.musinsa.catalog.brand.dto.BrandDto;
import com.musinsa.catalog.hibernate.entity.BrandEntity;
import com.musinsa.common.Code;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
  List<BrandDto> findAllByDeleteYn(Code.YnType deleteYn);

  @Modifying
  @Transactional
  @Query("UPDATE BrandEntity b SET b.name = :name, b.updatedId = :userId WHERE b.id = :id")
  void updateBrandName(long id, String name, String userId);

  @Modifying
  @Transactional
  @Query("UPDATE BrandEntity b SET b.deleteYn = 'Y', b.updatedId = :userId WHERE b.id IN :ids")
  void deleteAllById(List<Long> ids, String userId);
}
