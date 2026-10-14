package com.example.socialmedia.controller;


import com.example.socialmedia.entity.Comment;
import com.example.socialmedia.dto.CommentDTO;
import com.example.socialmedia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(Comment comment){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createComment(comment));
    }

    //here multiple comments so List and always in DTO format so CommentDTO
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(service.getCommentsByPostId(postId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Comment successfully");
    }



}
