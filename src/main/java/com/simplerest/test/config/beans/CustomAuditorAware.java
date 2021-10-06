package com.simplerest.test.config.beans;

import com.simplerest.test.model.entity.auth.User;
import com.simplerest.test.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomAuditorAware implements AuditorAware<User> {
    private final UserService userService;

    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.of(
                ((User) userService.loadUserByUsername(
                        SecurityContextHolder.getContext().getAuthentication().getName()
                ))
        );
    }
}
