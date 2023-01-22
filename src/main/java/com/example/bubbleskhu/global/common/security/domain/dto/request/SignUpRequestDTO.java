package com.example.bubbleskhu.global.common.security.domain.dto.request;

import com.example.bubbleskhu.major.domain.Major;
import com.example.bubbleskhu.model.Grade;
import lombok.Data;

@Data
public class SignUpRequestDTO {
    private String name;
    private Major major;
    private Grade grade;
    private String userId;
    private String userPassword;
}
