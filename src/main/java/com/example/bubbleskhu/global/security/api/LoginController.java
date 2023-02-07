package com.example.bubbleskhu.global.security.api;

import com.example.bubbleskhu.global.security.application.LoginService;
import com.example.bubbleskhu.global.security.domain.dto.LogoutRequestDTO;
import com.example.bubbleskhu.global.security.domain.dto.request.SignUpRequestDTO;
import com.example.bubbleskhu.global.security.domain.dto.TokenDTO;
import com.example.bubbleskhu.global.security.domain.dto.request.LoginRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "sign in", description = "회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        loginService.signup(signUpRequestDTO);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PostMapping("/signout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequestDTO logoutRequestDTO) {
        loginService.logout(logoutRequestDTO);
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }
}