package com.thelastofus.dto.mail;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class MessageDeserializer extends JsonbDeserializer<Message> {
    public MessageDeserializer() {
        super(Message.class);
    }
}
