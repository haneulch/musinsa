package com.musinsa.catalog.security;

import com.musinsa.catalog.common.code.ErrorCode;
import com.musinsa.catalog.common.exception.CatalogException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Slf4j
@Component
public class JwtTokenParser {
  @Value("${secret.name}")
  private String name;

  @Value("${secret.key}")
  private String secret;

  public Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String getUserIdFromToken(String token) {
    try {
      Claims claims = Jwts.parserBuilder()
          .setSigningKey(getSigningKey())
          .build()
          .parseClaimsJws(token)
          .getBody();
      return claims.get(name, String.class);

    } catch (ExpiredJwtException expiredJwtException) {
      log.error("Token Expired: {}", expiredJwtException.getMessage());
      throw new CatalogException(ErrorCode.TOKEN_EXPIRED);
    } catch (Exception e) {
      log.error("Token Error: {}", e.getMessage());
      throw new CatalogException(ErrorCode.INVALID_TOKEN);
    }
  }
}
