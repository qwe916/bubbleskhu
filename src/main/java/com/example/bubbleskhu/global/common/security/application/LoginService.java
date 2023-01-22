package com.example.bubbleskhu.global.common.security.application;

import com.example.bubbleskhu.global.common.security.domain.dto.TokenDTO;
import com.example.bubbleskhu.global.common.security.domain.dto.request.LoginRequestDTO;
import com.example.bubbleskhu.global.common.security.domain.dto.request.SignUpRequestDTO;
import com.example.bubbleskhu.global.common.security.jwt.JwtTokenProvider;
import com.example.bubbleskhu.global.error.CustomException;
import com.example.bubbleskhu.member.dao.UserRepository;
import com.example.bubbleskhu.member.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate redisTemplate;
    private final PasswordEncoder passwordEncoder;


    public TokenDTO login(LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUserId(), loginRequestDTO.getUserPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDTO tokenDTO = tokenProvider.createToken(authentication);

        redisTemplate.opsForValue().set( authentication.getName(), tokenDTO.getRefreshToken(), tokenDTO.getRefreshTokenExpirationTime().getTime(), TimeUnit.MILLISECONDS);

        return tokenDTO;

    }

    public void signUp(SignUpRequestDTO signUpRequestDTO) {
        if (userRepository.existsByUserId(signUpRequestDTO.getUserId())) {
            throw new CustomException("이미 존재하는 회원 입니다.", HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            User user = User.builder()
                    .userId(signUpRequestDTO.getUserId())
                    .userPassword(signUpRequestDTO.getUserPassword())
                    .major(signUpRequestDTO.getMajor())
                    .grade(signUpRequestDTO.getGrade())
                    .name(signUpRequestDTO.getName())
                    .build();
            userRepository.save(user);
        }
    }
}
