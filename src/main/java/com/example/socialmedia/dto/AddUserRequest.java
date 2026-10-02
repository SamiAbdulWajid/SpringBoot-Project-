package com.example.socialmedia.dto;

import lombok.Data;

@Data
public class AddUserRequest {
    private String username;
    private String password;
    private Integer age;
}
