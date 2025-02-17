package com.musinsa.catalog.web;

import com.musinsa.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/catalog")
@RequiredArgsConstructor
public class CatalogController {
  private final CatalogService catalogService;

}
