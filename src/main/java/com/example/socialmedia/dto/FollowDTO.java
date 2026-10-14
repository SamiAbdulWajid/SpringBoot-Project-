package com.example.socialmedia.dto;

import com.example.socialmedia.entity.User;

import lombok.Builder;
import lombok.Data;

@Builder 
@Data 

public class FollowDTO {
    private Long id;
    private Long followerId;
    private String followerUsername;
    private Long followingId;
    private String followingUsername;
}
