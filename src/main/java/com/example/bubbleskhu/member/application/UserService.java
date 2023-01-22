package com.example.bubbleskhu.member.application;

import com.example.bubbleskhu.global.common.security.domain.dto.TokenDTO;
import com.example.bubbleskhu.global.common.security.domain.dto.request.LoginRequestDTO;
import com.example.bubbleskhu.global.common.security.jwt.JwtTokenProvider;
import com.example.bubbleskhu.member.dao.UserRepository;
import com.example.bubbleskhu.member.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Optional<User> findUser(String username) {
        return userRepository.findByUserId(username);
    }
}
