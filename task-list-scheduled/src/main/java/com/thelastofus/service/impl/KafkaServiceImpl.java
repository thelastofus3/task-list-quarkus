package com.thelastofus.service.impl;

import com.thelastofus.dto.mail.MailType;
import com.thelastofus.dto.mail.Message;
import com.thelastofus.service.KafkaService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Slf4j
@ApplicationScoped
public class KafkaServiceImpl implements KafkaService {

    @Channel("EMAIL_SENDING_TASKS")
    Emitter<Message> emitter;

    @Override
    public void send(String email, String username, String body) {
        Message message = Message.builder()
                .username(username)
                .email(email)
                .title("Your Task Summary")
                .body(body)
                .type(MailType.REMAINDER)
                .build();
        log.info("Sending message: {}", message);
        emitter.send(message);
    }
}
