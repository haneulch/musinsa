package com.musinsa.catalog.persistence.repository;

import com.musinsa.catalog.common.code.YnType;
import com.musinsa.catalog.persistence.entity.CategoryEntity;
import com.musinsa.catalog.persistence.vo.CategoryVO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
  List<CategoryVO> findAllByDeleteYn(YnType deleteYn);

  Optional<CategoryEntity> findByCode(String code);

  @Modifying
  @Transactional
  @Query("UPDATE CategoryEntity c SET c.name = :name, c.updatedId = :updatedId WHERE c.code = :code")
  void updateCategoryName(String code, String name, String updatedId);

  @Modifying
  @Transactional
  @Query("UPDATE CategoryEntity c SET c.deleteYn = 'Y', c.updatedId = :userId WHERE c.id IN :ids")
  void deleteAllById(List<Long> ids, String userId);
}
