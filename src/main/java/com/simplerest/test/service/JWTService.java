package com.simplerest.test.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String generateToken(UserDetails userDetails);
    String extractUsername(String token);
    String extractAuthorities(String token);
}
