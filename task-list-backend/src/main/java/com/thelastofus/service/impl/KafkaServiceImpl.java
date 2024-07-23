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
                .type(type)
                .build());
    }

}
