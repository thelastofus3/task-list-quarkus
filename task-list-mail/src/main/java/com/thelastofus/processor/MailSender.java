package com.thelastofus.processor;

import com.thelastofus.dto.mail.Message;
import com.thelastofus.template.Templates;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;


@ApplicationScoped
public class MailSender {

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
