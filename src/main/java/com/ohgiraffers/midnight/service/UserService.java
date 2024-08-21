package com.ohgiraffers.midnight.service;

import com.ohgiraffers.midnight.dto.UserRegistRequestDTO;
import com.ohgiraffers.midnight.entity.User;
import com.ohgiraffers.midnight.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByUserName(String username) {
        return userRepository.findUserByUserName(username);
    }

    public void registUser(UserRegistRequestDTO userRegistRequestDTO) {
        userRepository.save(new User(
                userRegistRequestDTO.getUsername(),
                userRegistRequestDTO.getPassword()
        ));
    }
}
