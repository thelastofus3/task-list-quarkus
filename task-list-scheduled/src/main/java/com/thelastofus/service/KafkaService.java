package com.thelastofus.service;

import com.thelastofus.dto.mail.Message;

public interface KafkaService {

    public void send(String email, String username, String body);

}
