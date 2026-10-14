package com.example.socialmedia.repo;

import com.example.socialmedia.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRespository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);
}
