package com.example.socialmedia.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private String error;
    private Integer status;
    private LocalDateTime timestamp = LocalDateTime.now();
}
