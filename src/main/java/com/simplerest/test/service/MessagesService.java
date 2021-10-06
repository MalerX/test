package com.simplerest.test.service;

import com.simplerest.test.dto.InputMessage;
import com.simplerest.test.dto.ArchMessages;
import lombok.NonNull;

import java.security.Principal;

/**
 * Сервис для работы с {@link com.simplerest.test.model.entity.Message}.
 * Позволяет добавлять и получать из БД сообщения авторизованного пользователя.
 */
public interface MessagesService {

    /**
     * Метод для добавления сообщений в БД.
     * @param inputMessage -- объект DTO содержащий имя пользователя и текст сообщения.
     */
    void add(@NonNull InputMessage inputMessage);

    /**
     * Получить последние N сообщений из БД для пользователя principal.
     * @param quantityMessages -- количество сообщений, которое необходимо вернуть из БД.
     * @param principal -- авторизованный пользователь.
     * @return UserMessages -- объект содержит имя пользователя и список его последних сообщений.
     * @throws Exception в случае отсутствия сообщений в БД у авторизованного пользователя.
     */
    ArchMessages getLastMessages(@NonNull int quantityMessages, @NonNull Principal principal) throws Exception;
}
