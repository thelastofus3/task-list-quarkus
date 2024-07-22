package com.thelastofus.service;

import com.thelastofus.dto.mail.Message;

public interface KafkaService {

    void send(String email, String username);

}
