package com.example.bubbleskhu.bubble.api;

import com.example.bubbleskhu.bubble.application.BubbleService;
import com.example.bubbleskhu.bubble.domain.dto.request.BubbleRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BubbleController {
    private final BubbleService bubbleService;


    @GetMapping("/bubbles")
    public ResponseEntity<String> findAllBubbles() {
        return ResponseEntity.ok("비로그인 버블 모두 조회");
    }

    @PreAuthorize("isAuthenticated() and  (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PostMapping("/bubbles")
    public ResponseEntity<String> saveBubble(@RequestBody BubbleRequestDTO bubbleRequestDTO) {
        bubbleService.saveBubble(bubbleRequestDTO);
        return ResponseEntity.ok("Bubble 저장");
    }



}
