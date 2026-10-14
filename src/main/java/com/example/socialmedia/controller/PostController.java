package com.example.socialmedia.controller;

import com.example.socialmedia.dto.PostDTO;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody Post post){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPost(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getPostById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostDTO>> getPostsByUserId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getPostsByUserId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Post deleted Successfully");
    }

}
