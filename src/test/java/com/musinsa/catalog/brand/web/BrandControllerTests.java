package com.musinsa.catalog.brand.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.catalog.persistence.entity.BrandEntity;
import com.musinsa.catalog.security.JwtTokenParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Maps;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
public class BrandControllerTests {
  private final MockMvc mockMvc;
  private final ObjectMapper objectMapper;
  private final JwtTokenParser jwtTokenParser;

  @Test
  @DisplayName("/api/brand 테스트")
  void getBrand() throws Exception {
    mockMvc.perform(get("/api/brand"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(9))
        .andExpect(jsonPath("$[0].name").value("A"))
        .andExpect(jsonPath("$[8].name").value("I"));
  }

  @Test
  @DisplayName("/api/brand/create 테스트")
  void postBrandCreate() throws Exception {
    BrandEntity expected = BrandEntity.builder().name("A-3").build();

    mockMvc.perform(post("/api/brand/create")
        .header("Authorization", String.format("Bearer %s", generateToken("system")))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(expected)))
        .andExpect(status().isOk());
  }

  private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간 (밀리초 단위)

  public String generateToken(String username) {
    return Jwts.builder()
        .setClaims(Maps.of("USER_ID", username).build())
        .setSubject(username) // 사용자 정보
        .setIssuedAt(new Date()) // 발급 시간
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 만료 시간
        .signWith(jwtTokenParser.getSigningKey()) // 서명 (HS256)
        .compact(); // JWT 생성
  }
}
