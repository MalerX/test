package com.simplerest.test.dto;

import com.simplerest.test.model.entity.Message;

import java.util.List;

public record ArchMessages(String name, List<MessageDTO> messages) {
}
