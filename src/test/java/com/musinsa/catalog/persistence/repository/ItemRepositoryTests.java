package com.musinsa.catalog.persistence.repository;

import com.musinsa.catalog.common.code.YnType;
import com.musinsa.catalog.persistence.entity.ItemEntity;
import com.musinsa.catalog.persistence.vo.ItemVO;
import com.musinsa.catalog.persistence.vo.LowestItemByCategoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@TestConstructor(autowireMode = AutowireMode.ALL)
@RequiredArgsConstructor
class ItemRepositoryTests {
  private final ItemRepository itemRepository;

  @DisplayName("카테고리별 최저가격 테스트")
  @Test
  void lowestCategory() {
    // Given
    List<ItemVO> allItems = itemRepository.findAllByDeleteYn(YnType.N);

    // When
    Map<String, Integer> expected = allItems.stream()
        .collect(Collectors.groupingBy(
            ItemVO::categoryName,
            Collectors.collectingAndThen(
                Collectors.minBy(Comparator.comparingInt(ItemVO::price)),
                minItem -> minItem.map(ItemVO::price).orElse(Integer.MAX_VALUE)
            )
        ));

    // Then
    List<LowestItemByCategoryVO> result = itemRepository.findLowestItemsByCategory();
    for (LowestItemByCategoryVO item : result) {
      int expectedPrice = expected.getOrDefault(item.categoryName(), Integer.MAX_VALUE);
      log.info("카테고리: {}, 최저가: {}, 예상 최저가: {}", item.categoryName(), item.price(), expectedPrice);

      assertThat(item.price()).isEqualTo(expectedPrice);
    }
  }

  @DisplayName("단일 카테고리 최저가격/최고가격")
  @Test
  void minMaxCategory() {
    // Given
    List<ItemEntity> allItems = itemRepository.findAll();
    List<ItemEntity> notDeletedItems = allItems.stream().filter((item) -> item.getDeleteYn() == YnType.N).toList();
    Set<String> categories = notDeletedItems.stream().map(ItemEntity::getCategoryCode).collect(Collectors.toSet());

    categories.forEach(categoryCode -> {
      // When
      List<LowestItemByCategoryVO> result = itemRepository.findMinMaxByCategory(categoryCode);
      Optional<Integer> minPriceOpt = notDeletedItems.stream()
          .filter(item -> item.getCategoryCode().equals(categoryCode))
          .map(ItemEntity::getPrice)
          .min(Integer::compareTo);

      Optional<Integer> maxPriceOpt = notDeletedItems.stream()
          .filter(item -> item.getCategoryCode().equals(categoryCode))
          .map(ItemEntity::getPrice)
          .max(Integer::compareTo);

      int expectedMinPrice = minPriceOpt.orElse(Integer.MAX_VALUE);
      int expectedMaxPrice = maxPriceOpt.orElse(Integer.MIN_VALUE);

      // Then
      log.info("카테고리: {}, 예상 최저가: {}, 예상 최고가: {}", categoryCode, expectedMinPrice, expectedMaxPrice);

      assertThat(result).isNotEmpty();
      assertThat(result.stream().map(LowestItemByCategoryVO::price).distinct()).containsExactlyInAnyOrder(expectedMinPrice, expectedMaxPrice);
    });
  }

  @DisplayName("단일 브랜드 최저가격")
  @Test
  void lowestBrand() {
    // Given
    List<ItemVO> allItems = itemRepository.findAllByDeleteYn(YnType.N);

    // When
    Map<String, Integer> totalPriceByBrand = allItems.stream()
        .collect(Collectors.groupingBy(ItemVO::brandName, Collectors.summingInt(ItemVO::price)));

    Optional<Integer> minTotalPriceOpt = totalPriceByBrand.values().stream().min(Integer::compareTo);

    int minTotalPrice = minTotalPriceOpt.orElse(0);

    // Then
    List<LowestItemByCategoryVO> result = itemRepository.findLowestItemsByBrand();

    assertThat(result).isNotEmpty();

    int resultPrice = result.stream().mapToInt(LowestItemByCategoryVO::price).sum();
    log.info("브랜드: {}, 최저가: {}, 예상 최저가: {}", result.get(0).brandName(), resultPrice, minTotalPrice);
    assertThat(resultPrice).isEqualTo(minTotalPrice);
  }
}
