package com.example.bubbleskhu.post.dao;

import com.example.bubbleskhu.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
