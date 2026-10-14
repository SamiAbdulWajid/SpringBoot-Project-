package com.example.socialmedia.controller;

import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.entity.Like;
import com.example.socialmedia.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeService service;

    @GetMapping("/like/{postId}") //find users from postId
    public ResponseEntity<List<UserDTO>> findUsersByPostId(@PathVariable Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(service.findUsersByPostId(postId));
    }

    @PostMapping
    public ResponseEntity<String> likePost(@RequestBody Like like){
        service.likePost(like);

        return ResponseEntity.status(HttpStatus.CREATED).body("Post liked successfully");
    }

    @DeleteMapping("/{userId}/{postId}")
    public ResponseEntity<String> unlikePost(@PathVariable Long userId,@PathVariable Long postId){
        service.unlikePost(userId,postId);

        return ResponseEntity.status(HttpStatus.OK).body("Post unliked successfully");
    }

}
