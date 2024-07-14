package com.thelastofus.service.impl;

import com.thelastofus.config.PasswordEncoder;
import com.thelastofus.dto.user.UserRequest;
import com.thelastofus.dto.user.UserResponse;
import com.thelastofus.exception.EmailAlreadyExistsException;
import com.thelastofus.exception.ResourceNotFoundException;
import com.thelastofus.mapper.UserMapper;
import com.thelastofus.model.Role;
import com.thelastofus.model.User;
import com.thelastofus.repository.UserRepository;
import com.thelastofus.service.UserService;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@ApplicationScoped
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
//    @Cacheable(value = "UserService::getById", key = "#id")
    @CacheResult(cacheName = "userService::getById")
    public UserResponse getById(@CacheKey Long id) {
        User user = userRepository.findById(id);
        return userMapper.convertToUserResponse(user);
    }

    @Override
//    @Cacheable(value = "UserService::getByUsername", key = "#username")
    @CacheResult(cacheName = "userService::getByUsername")
    public UserResponse getByUsername(@CacheKey String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
//    @Cacheable(value = "UserService::getByEmail", key = "#email")
    @CacheResult(cacheName = "userService::getByeMAIL")
    public User getByEmail(@CacheKey String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

    }

    @Override
//    @Caching(cacheable = {
//            @Cacheable(value = "UserService::getByUsername", key = "userRequest.username"),
//            @Cacheable(value = "UserService::getByEmail", key = "userRequest.email")
//    })
    public User saveUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("This email is already taken");
        }
        return userRepository.save(User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role(Role.ROLE_USER)
                .build());
    }

}
