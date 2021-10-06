package com.simplerest.test.controller.impl;

import com.simplerest.test.controller.AuthorizationController;
import com.simplerest.test.dto.AuthRequest;
import com.simplerest.test.dto.AuthResponse;
import com.simplerest.test.service.AuthenticationFacade;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthorizationControllerImpl implements AuthorizationController {

    private final AuthenticationFacade authenticationFacade;

    @PostMapping("login")
    @Override
    public AuthResponse login(@NonNull AuthRequest authRequest) {
        log.info("IN login() -- Received an authorization request from the user \"{}\".", authRequest.name());
        return new AuthResponse(authenticationFacade.login(authRequest.name(), authRequest.password()));
    }
}
