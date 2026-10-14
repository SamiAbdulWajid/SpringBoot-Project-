package com.example.socialmedia.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDTO {

    private Long id;  //what is the no of this comment from all comments

    private String comment; //what he wrote

    private Long userId; //writing person id

    private Long postId; //for which video
}
