package com.simplerest.test.service.impl;

import com.simplerest.test.service.AuthenticationFacade;
import com.simplerest.test.service.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    private static final String TOKEN_PREFIX = "Bearer ";

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public String login(String username, String password) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            log.info("IN login() -- Access is allowed. Generate token for user {}", username);
        } catch (BadCredentialsException e) {
            log.warn("IN login() -- Bad login or password. Access is denied.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password not valid.");
        }
        return TOKEN_PREFIX + jwtService.generateToken(((UserDetails) authentication.getPrincipal()));
    }
}
