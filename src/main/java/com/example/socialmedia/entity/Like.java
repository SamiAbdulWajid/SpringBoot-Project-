package com.example.socialmedia.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id of the post

    private Long userId; //who liked the post - user tracking

    private Long postId; //which post was liked - post tracking
}
