package com.example.socialmedia.repo;

import com.example.socialmedia.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {

   List<Like> findUsersByPostId(Long postId); //users nikalne postId mai se 

   void deleteByUserIdAndPostId(Long userId,Long postId);//unlike post 

   Optional<Like> findByUserIdAndPostId(Long userId,Long postId); //to check whether it is already liked or not
}
