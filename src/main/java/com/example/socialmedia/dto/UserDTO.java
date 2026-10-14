package com.example.socialmedia.dto;

import lombok.Builder;
import lombok.Data;

@Data //Data -> Lombok is for creating getters and setters 
@Builder //Builder is for that .builder().age().build().toList()if it is list 
public class UserDTO {
    private String username;
    private Integer age;
}
