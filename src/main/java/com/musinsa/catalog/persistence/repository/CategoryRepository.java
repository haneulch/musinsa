package com.musinsa.catalog.persistence.repository;

import com.musinsa.catalog.common.code.YnType;
import com.musinsa.catalog.persistence.entity.CategoryEntity;
import com.musinsa.catalog.persistence.vo.CategoryVO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
  List<CategoryVO> findAllByDeleteYn(YnType deleteYn);

  Optional<CategoryEntity> findByCode(String code);

  @Modifying
  @Transactional
  @Query("UPDATE CategoryEntity c SET c.name = :name, c.updatedId = :updatedId WHERE c.id = :id")
  void updateCategoryName(@Param("id") long id, @Param("name") String name, @Param("updatedId") String updatedId);

  @Modifying
  @Transactional
  @Query("UPDATE CategoryEntity c SET c.deleteYn = 'Y', c.updatedId = :userId WHERE c.id IN :ids")
  void deleteAllById(@Param("ids") List<Long> ids, @Param("userId") String userId);
}
