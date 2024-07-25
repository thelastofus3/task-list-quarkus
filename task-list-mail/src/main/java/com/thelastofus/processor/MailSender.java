package com.thelastofus.processor;

import com.thelastofus.dto.mail.MailType;
import com.thelastofus.dto.mail.Message;
import com.thelastofus.template.Templates;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ApplicationScoped
public class MailSender {


    private static final Logger log = LoggerFactory.getLogger(MailSender.class);

    @Incoming("EMAIL_SENDING_TASKS")
    public Uni<Void> send(Message message) {
        switch (message.getType()) {
            case REMAINDER -> {
                return Templates.remainder(message)
                        .to(message.getEmail())
                        .subject(message.getTitle())
                        .send();
            }
            case REGISTRATION -> {
                return Templates.registration(message)
                        .to(message.getEmail())
                        .subject(message.getTitle())
                        .send();
            }
            case null, default -> {
                return Uni.createFrom().voidItem();
            }
        }
    }

}
