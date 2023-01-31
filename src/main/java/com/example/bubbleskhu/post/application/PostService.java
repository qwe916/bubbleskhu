package com.example.bubbleskhu.post.application;

import com.example.bubbleskhu.bubble.dao.BubbleRepository;
import com.example.bubbleskhu.bubble.domain.Bubble;
import com.example.bubbleskhu.global.error.CustomException;
import com.example.bubbleskhu.post.dao.PostRepository;
import com.example.bubbleskhu.post.domain.Post;
import com.example.bubbleskhu.post.domain.dto.request.PostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final BubbleRepository bubbleRepository;

    private final PostRepository postRepository;
    @Transactional
    public void updatePost(Long id, PostRequestDTO postRequestDTO) {
        Bubble bubble = bubbleRepository.findById(id).orElseThrow(() -> new CustomException("Bubble이 존재하지 않습니다.", HttpStatus.NOT_FOUND));
        bubble.getPost().updatePost(postRequestDTO);
    }
}
