package com.example.beinus.config;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// To let the JwtAuthenticationFilter read the Http Request every time, set "extends OncePerRequestFilter".
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) throws ServletException, IOException {
        // The header that contains the JWT token.
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userName;

        // If the authHeader is null or the authHeader doesn't start with "Bearer ".
        // Because the Bearer token should always start with the keyword "Bearer ".
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Not attempting to authenticate the request using token.
            // Instead, simply passing the request and response along to the next entity in the filter chain.
            // "I won't handle this, let the next filter or the servlet itself decide what to do."
            filterChain.doFilter(request, response);
            return;
        }

        // The index of next to the "Bearer " is 7.
        jwt = authHeader.substring(7);

        // Extract the userName from JWT token
        userName = jwtService.extractUsername(jwt);     // Extract the Username by parsing the jwt token.
    }
}
