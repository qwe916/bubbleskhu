package com.example.bubbleskhu.bubble.api;

import com.example.bubbleskhu.bubble.application.BubbleService;
import com.example.bubbleskhu.bubble.dao.dto.request.BubbleRequestDTO;
import com.example.bubbleskhu.bubble.dao.dto.response.BubbleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BubbleController {
    private final BubbleService bubbleService;


    @GetMapping("/bubbles")
    public ResponseEntity<List<BubbleResponseDTO>> findAllBubbles() {
        return ResponseEntity.ok(bubbleService.findAll());
    }

    @PreAuthorize("isAuthenticated() and  (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PostMapping("/bubbles")
    public ResponseEntity<String> saveBubble(Principal principal, @RequestBody BubbleRequestDTO bubbleRequestDTO) {
        bubbleService.saveBubble(principal,bubbleRequestDTO);
        return ResponseEntity.ok("Bubble 저장");
    }

    @PreAuthorize("isAuthenticated() and  (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @GetMapping("/bubbles/{id}")
    public ResponseEntity<BubbleResponseDTO> findBubbleById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bubbleService.findBubbleById(id));
    }

    @PreAuthorize("isAuthenticated() and  (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @DeleteMapping("/bubbles/{id}")
    public ResponseEntity<String> deleteBubbleById(@PathVariable("id") Long id) {
        bubbleService.deleteBubbleById(id);
        return ResponseEntity.ok("Bubble 삭제완료");
    }


}
