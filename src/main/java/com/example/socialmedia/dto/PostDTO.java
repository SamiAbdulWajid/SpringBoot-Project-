package com.example.socialmedia.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDTO {
    private Long id;
    private String content;

}
