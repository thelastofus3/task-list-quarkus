package com.thelastofus.service.impl;

import com.thelastofus.dto.mail.MailType;
import com.thelastofus.dto.mail.Message;
import com.thelastofus.service.KafkaService;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class KafkaServiceImpl implements KafkaService {

    @Channel("EMAIL_SENDING_TASKS")
    Emitter<Message> emitter;

    @Override
    public void send(String email, String username, MailType type) {
        emitter.send(Message.builder()
                .username(username)
                .email(email)
                .title("Welcome to Task List")
                .body("We're thrilled to welcome you to TaskList, your new go-to platform for efficient task management and productivity boosting!")
                .type(type)
                .build());
    }

}
