package com.thelastofus.service.impl;

import com.thelastofus.dto.user.UserRequest;
import com.thelastofus.dto.user.UserResponse;
import com.thelastofus.exception.EmailAlreadyExistsException;
import com.thelastofus.exception.ResourceNotFoundException;
import com.thelastofus.model.Role;
import com.thelastofus.model.User;
import com.thelastofus.repository.UserRepository;
import com.thelastofus.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Cacheable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@ApplicationScoped
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
//    UserMapper userMapper;
//    PasswordEncoder passwordEncoder;

    @Override
//    @Cacheable(value = "UserService::getById", key = "#id")
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id);
//        return userMapper.convertToUserResponse(user);
        return null;
    }

    @Override
//    @Cacheable(value = "UserService::getByUsername", key = "#username")
    public UserResponse getByUsername(String username) {
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
    public User getByEmail(String email) {
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
//        return userRepository.save(User.builder()
//                .username(userRequest.getUsername())
//                .email(userRequest.getEmail())
//                .password(userRequest.getPassword())
//                .role(Role.ROLE_USER)
//                .build());
//    }
        return null;
    }

}
