package com.musinsa.catalog.config.user.annotation;

import com.musinsa.catalog.security.JwtTokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserIdHandler implements HandlerMethodArgumentResolver {
  private final JwtTokenParser jwtUtil;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(UserId.class) &&
        parameter.getParameterType().equals(String.class);
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) {

    String token = webRequest.getHeader("Authorization");
    if (token == null || !token.startsWith("Bearer ")) {
      throw new IllegalArgumentException("Invalid token");
    }
    token = token.substring(7);
    return jwtUtil.getUserIdFromToken(token);
  }
}
