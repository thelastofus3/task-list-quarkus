package com.thelastofus.service.impl;

import com.thelastofus.model.User;
import com.thelastofus.repository.UserRepository;
import com.thelastofus.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.listAll();
    }

}
