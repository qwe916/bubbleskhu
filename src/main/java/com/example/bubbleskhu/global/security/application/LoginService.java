package com.example.bubbleskhu.global.security.application;

import com.example.bubbleskhu.global.security.dao.RoleRepository;
import com.example.bubbleskhu.global.security.domain.dto.LogoutRequestDTO;
import com.example.bubbleskhu.global.security.domain.dto.TokenDTO;
import com.example.bubbleskhu.global.security.domain.dto.request.LoginRequestDTO;
import com.example.bubbleskhu.global.security.domain.dto.request.SignUpRequestDTO;
import com.example.bubbleskhu.global.security.jwt.JwtTokenProvider;
import com.example.bubbleskhu.global.error.CustomException;
import com.example.bubbleskhu.major.dao.MajorRepository;
import com.example.bubbleskhu.user.dao.UserRepository;
import com.example.bubbleskhu.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final RoleRepository roleRepository;
    private final MajorRepository majorRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate redisTemplate;
    private final PasswordEncoder passwordEncoder;


    public TokenDTO login(LoginRequestDTO loginRequestDTO) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUserId(), loginRequestDTO.getUserPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDTO tokenDTO = tokenProvider.createToken(authentication);

        redisTemplate.opsForValue().set( authentication.getName(), tokenDTO.getRefreshToken(), tokenDTO.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return tokenDTO;

    }

    public void signup(SignUpRequestDTO signUpRequestDTO) {
        if (userRepository.existsByUsername(signUpRequestDTO.getUserId())) {
            throw new CustomException("이미 존재하는 회원 입니다.", HttpStatus.UNPROCESSABLE_ENTITY);
        } else {

            User user = User.builder()
                    .username(signUpRequestDTO.getUserId())
                    .userPassword(passwordEncoder.encode(signUpRequestDTO.getUserPassword()))
                    .major(majorRepository.findById(signUpRequestDTO.getMajorId()).get())
                    .grade(signUpRequestDTO.getGrade())
                    .name(signUpRequestDTO.getName())
                    .roles(Collections.singletonList(roleRepository.findByName("ROLE_USER")))
                    .build();
            userRepository.save(user);
        }
    }

    public ResponseEntity<?> logout(LogoutRequestDTO logoutRequestDTO) {
        if (!tokenProvider.validateToken(logoutRequestDTO.getAccessToken())) {
            return new ResponseEntity<>("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = tokenProvider.getAuthentication(logoutRequestDTO.getAccessToken());

        if (redisTemplate.opsForValue().get(authentication.getName()) != null) {
            redisTemplate.delete(authentication.getName());
        }

        Long expiration = tokenProvider.getExpiration(logoutRequestDTO.getAccessToken());

        redisTemplate.opsForValue().set(logoutRequestDTO.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);

        return ResponseEntity.ok("로그아웃 되었습니다.");
    }


}
