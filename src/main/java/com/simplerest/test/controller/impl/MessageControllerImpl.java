package com.simplerest.test.controller.impl;

import com.simplerest.test.controller.MessageController;
import com.simplerest.test.dto.InputMessage;
import com.simplerest.test.dto.ArchMessages;
import com.simplerest.test.service.MessagesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/messages")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class MessageControllerImpl implements MessageController {
    private final MessagesService messagesService;

    @PostMapping("add")
    @Override
    public void addMessage(@NonNull InputMessage inputMessage) {
        messagesService.add(inputMessage);
    }

    @GetMapping("last")
    @Override
    public ArchMessages getLastMessages(@NonNull int quantityMessages, @NonNull Principal principal) throws Exception {
            return messagesService.getLastMessages(quantityMessages, principal);
    }
}
