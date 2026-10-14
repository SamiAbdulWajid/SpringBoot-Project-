package com.example.socialmedia.controller;

import com.example.socialmedia.dto.FollowDTO;
import com.example.socialmedia.entity.Follow;
import com.example.socialmedia.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowController {

    @Autowired
    private FollowService service;

    @PostMapping
    public ResponseEntity<String> followUser(@RequestBody Follow follow){

        service.followUser(follow);

        return ResponseEntity.status(HttpStatus.CREATED).body("User followed successfully");

    }

    @DeleteMapping("/followerId/{followerId}/followingId/{followingId}")
    public ResponseEntity<String> unfollowUser(@RequestBody Long followerId , @RequestBody Long followingId ){
        service.unfollowUser(followerId,followingId);

        return ResponseEntity.status(HttpStatus.OK).body("Unfollowed 💔");
    }

    @GetMapping("/followers/{followingId}")
    public ResponseEntity<List<FollowDTO>> getFollowersInfo(@PathVariable Long followingId){
        return ResponseEntity.status(HttpStatus.OK).body(service.getFollowersInfo(followingId)) ;
    }

    @GetMapping("/following/{followerId}")
    public ResponseEntity<List<FollowDTO>> getFollowerFollowings(@PathVariable Long followerId){
        return ResponseEntity.status(HttpStatus.OK).body(service.getFollowerFollowings(followerId));
    }
}
