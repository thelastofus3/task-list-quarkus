package com.thelastofus.processor;

import com.thelastofus.dto.mail.MailType;
import com.thelastofus.dto.mail.Message;
import com.thelastofus.template.Templates;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Incoming;


@ApplicationScoped
public class MailSender {


    @Incoming("EMAIL_SENDING_TASKS")
    public Uni<Void> send(Message message) {
        if (message.getType().equals(MailType.REGISTRATION)) {
            return Templates.registration(message.getUsername())
                    .to(message.getEmail())
                    .subject(message.getTitle())
                    .send();
        } else {
            return Uni.createFrom().voidItem();
        }
    }

}
