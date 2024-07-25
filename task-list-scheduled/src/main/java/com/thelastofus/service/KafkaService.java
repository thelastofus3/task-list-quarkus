package com.thelastofus.service;


public interface KafkaService {

    public void send(String email, String username, String body);

}
