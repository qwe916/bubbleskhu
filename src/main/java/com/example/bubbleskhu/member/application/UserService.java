package com.example.bubbleskhu.member.application;

import com.example.bubbleskhu.member.dao.UserRepository;
import com.example.bubbleskhu.member.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Optional<User> findUser(String username) {
        return userRepository.findByUserId(username);
    }
}
