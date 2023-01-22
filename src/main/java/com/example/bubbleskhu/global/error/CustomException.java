package com.example.bubbleskhu.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
}
