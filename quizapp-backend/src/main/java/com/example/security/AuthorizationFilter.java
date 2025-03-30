package com.example.quizapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

  @Value("${admin.pin}")
  private String adminPin; // ← 通常はすべて小文字推奨（Spring Bootの規約）

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
          throws ServletException, IOException {

      boolean isProtectedMethod =
              HttpMethod.POST.matches(request.getMethod()) ||
              HttpMethod.PUT.matches(request.getMethod()) ||
              HttpMethod.PATCH.matches(request.getMethod()) ||
              HttpMethod.DELETE.matches(request.getMethod());

      boolean isTargetPath = request.getRequestURI().startsWith("/api/quizzes");

      if (isProtectedMethod && isTargetPath) {
          String authHeader = request.getHeader("Authorization");
          if (authHeader == null || !authHeader.startsWith("Bearer ")) {
              response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
              return;
          }

          String token = authHeader.substring("Bearer ".length()).trim();
          if (!adminPin.equals(token)) {
              response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid PIN token");
              return;
          }
      }

      filterChain.doFilter(request, response);
  }
}
