package com.example.bubbleskhu.bubble.api;

import com.example.bubbleskhu.bubble.application.BubbleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BubbleController {
    private final BubbleService bubbleService;

    @Secured("ROLE_ANONYMOUS")
    @GetMapping("/bubbles")
    public ResponseEntity<String> findAllBubbles() {
        return ResponseEntity.ok("비로그인 버블 모두 조회");
    }


    @GetMapping("/users/bubbles")
    public ResponseEntity<String> findAllBubbles_USER() {
        return ResponseEntity.ok("로그인 버블 모두 조회");
    }


}
