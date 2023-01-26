package com.example.bubbleskhu.global.security.api;

import com.example.bubbleskhu.global.security.application.LoginService;
import com.example.bubbleskhu.global.security.domain.dto.LogoutRequestDTO;
import com.example.bubbleskhu.global.security.domain.dto.request.SignUpRequestDTO;
import com.example.bubbleskhu.global.security.domain.dto.TokenDTO;
import com.example.bubbleskhu.global.security.domain.dto.request.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/signin")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(loginService.login(loginRequestDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        loginService.signup(signUpRequestDTO);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PreAuthorize("isAuthenticated() and  hasAnyRole(ROLE_USER,ROLE_ADMIN)")
    @PostMapping("/signout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequestDTO logoutRequestDTO) {
        return ResponseEntity.ok(loginService.logout(logoutRequestDTO));
    }
}