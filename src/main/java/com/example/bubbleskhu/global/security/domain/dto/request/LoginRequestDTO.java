package com.example.bubbleskhu.global.security.domain.dto.request;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String userId;
    private String userPassword;
}
