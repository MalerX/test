package com.simplerest.test.service.impl;

import com.simplerest.test.dto.InputMessage;
import com.simplerest.test.dto.MessageDTO;
import com.simplerest.test.dto.ArchMessages;
import com.simplerest.test.model.entity.Message;
import com.simplerest.test.model.entity.auth.User;
import com.simplerest.test.model.repository.MessageRepository;
import com.simplerest.test.model.repository.UserRepository;
import com.simplerest.test.service.MessagesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
@Log4j2
public class MessagesServiceImpl implements MessagesService {
    private final MessageRepository messageRepository;
    private final EntityManager entityManager;
    private final UserRepository userRepository;

    @Override
    public void add(@NonNull InputMessage inputMessage) {
        Message message = new Message();
        message.setTextMessage(inputMessage.message());
        messageRepository.save(message);
    }

    @Override
    public ArchMessages getLastMessages(@NonNull int quantityMessages, @NonNull Principal principal) throws Exception {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        List<Message> messages = getMessages(user, 0, quantityMessages);

        if (messages.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Message for user %s not found", principal.getName()));
        }
        List<MessageDTO> messageDTOS = messages.stream()
                .map(message -> new MessageDTO(message.getTextMessage(), message.getDate()))
                .collect(Collectors.toList());
        return new ArchMessages(user.getUsername(), messageDTOS);
    }

    //        На стороне базы выбираем записи конкретного пользователя, сортируем и забираем n последних записей.
    private List<Message> getMessages(@NonNull User user, int offset, int quantity) {
        return entityManager
                .createQuery("SELECT m FROM Message m WHERE m.user = :user ORDER BY m.date ASC")
                .setParameter("user", user)
                .setFirstResult(offset)
                .setMaxResults(quantity)
                .getResultList();

    }
}
