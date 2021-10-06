package com.simplerest.test.controller;

import com.simplerest.test.dto.InputMessage;
import com.simplerest.test.dto.ArchMessages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Tag(
        name = "Сообщения.",
        description = "Контроллер для работы с сообщениями."
)
public interface MessageController {

    @Operation(
            summary = "Добавить сообщение.",
            description = "Добавить сообщение указанному пользователю.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сообщение успешно ассоциировано с пользователем и сохранено в БД."
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Доступ запрещён, пользователь не авторизован."
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Объект с именем пользователя и сообщением.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            summary = "Объект входящего сообщения",
                                            value = """
                                                    {
                                                        "name": "user",
                                                        "message": "new message"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
    )
    void addMessage(@NonNull @RequestBody InputMessage inputMessage);

    @Operation(
            summary = "Получить последние сообщения пользователя",
            description = "Получить N последних сообщений авторизованного пользователя.",
            parameters = {
                    @Parameter(
                            name = "quantity",
                            description = "Количество сообщений пользователя, которые необходимо вернуть"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Операция успешно выполнена.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ArchMessages.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Доступ запрещён, пользователь не авторизован.",
                            content = @Content()
                    )
            }
    )
    ArchMessages getLastMessages(@NonNull @RequestParam(name = "quantity") int quantityMessages, @NonNull Principal principal) throws Exception;
}
