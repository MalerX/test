package com.simplerest.test.controller;

import com.simplerest.test.dto.AuthRequest;
import com.simplerest.test.dto.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Аутентификация.",
        description = "Контроллер для аутентификации пользователей."
)
public interface AuthorizationController {

    @Operation(
            summary = "Аутентификация.",
            description = "Аутентификация пользователя через пару логин/пароль.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь успешно авторизован."
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content()
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Объект с полями name и password.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            summary = "Объект с авторизационными данными.",
                                            value = """
                                                    {
                                                        "name": "user login",
                                                        "password": "user password"
                                                    }"""
                                    )
                            }
                    )
            )
    )
        AuthResponse login(@NonNull @RequestBody AuthRequest authRequest);
}
