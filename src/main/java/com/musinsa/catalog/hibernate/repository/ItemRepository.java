package com.musinsa.catalog.hibernate.repository;

import com.musinsa.catalog.hibernate.entity.ItemEntity;
import com.musinsa.catalog.item.dto.ItemDto;
import com.musinsa.catalog.item.dto.LowestItemByCategoryDto;
import com.musinsa.common.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
  List<ItemDto> findAllByDeleteYn(Code.YnType deleteYn);

  @Query(value =
      "SELECT id, brandName, categoryName, price\n" +
          "FROM (\n" +
          "    SELECT *,\n" +
          "           ROW_NUMBER() OVER (PARTITION BY categoryCode ORDER BY price ASC) AS rn\n" +
          "    FROM ITEM\n" +
          "    WHERE deleteYn = 'N'\n" +
          ") ranked\n" +
          "WHERE rn = 1 ORDER BY categoryCode;",
      nativeQuery = true)
  List<LowestItemByCategoryDto> findLowestItemsByCategory();

  @Query(value = "SELECT id, brandName, categoryName, price\n" +
      "FROM ITEM i\n" +
      "WHERE i.brandName = (\n" +
      "    SELECT brandName\n" +
      "    FROM ITEM\n" +
      "    WHERE deleteYn = 'N'\n" +
      "    GROUP BY brandName\n" +
      "    ORDER BY SUM(price) ASC\n" +
      "    LIMIT 1) ORDER BY categoryCode;\n",
      nativeQuery = true)
  List<LowestItemByCategoryDto> findLowestItemsByBrand();

  @Query(value = "SELECT id, brandName, categoryName, price\n" +
      "FROM ITEM i\n" +
      "WHERE i.categoryCode = :categoryCode \n" +
      "AND (\n" +
      "    i.price = (SELECT MIN(price) FROM ITEM WHERE categoryCode = :categoryCode)\n" +
      "    OR\n" +
      "    i.price = (SELECT MAX(price) FROM ITEM WHERE categoryCode = :categoryCode)\n" +
      ");", nativeQuery = true)
  List<LowestItemByCategoryDto> findMinMaxByCategory(String categoryCode);
}
