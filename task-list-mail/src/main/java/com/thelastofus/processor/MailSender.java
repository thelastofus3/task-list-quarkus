package com.thelastofus.processor;

import com.thelastofus.dto.mail.Message;
import com.thelastofus.template.Templates;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
@RequiredArgsConstructor
public class MailSender {

    @GET
    @Incoming("EMAIL_SENDING_TASKS")
    public Uni<Void> send(Message message) {
        return Templates.page(message.getUsername())
                .to(message.getEmail())
                .subject(message.getTitle())
                .send();
    }

}
