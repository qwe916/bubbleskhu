package com.example.bubbleskhu.post.api;

import com.example.bubbleskhu.post.application.PostService;
import com.example.bubbleskhu.post.domain.dto.request.PostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PreAuthorize("isAuthenticated() and  (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PatchMapping("/bubbles/{id}/posts")
    public ResponseEntity<String> updatePost(@PathVariable("id") Long id, @RequestBody PostRequestDTO postRequestDTO) {
        postService.updatePost(id, postRequestDTO);
        return ResponseEntity.ok("수정되었습니다.");
    }
}
