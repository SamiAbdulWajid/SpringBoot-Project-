package com.example.socialmedia.dto;

import lombok.Data;

@Data
public class AddUserRequest {
    private String username;
    private String password;
    private Integer age;
}


//Difference btw UserDTO and AddUserRequestDTO is here 
//Displaying info we use UserDTO no password and for AddUserwetake user all the things
