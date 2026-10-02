package com.example.socialmedia.controller;

import java.util.*;

import com.example.socialmedia.dto.AddUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/socialmedia")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findUserById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> findByUsername(
            @PathVariable String username) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findByUsername(username));
    }

    @PostMapping("/add-user")
    public ResponseEntity<UserDTO> addUser(@RequestBody AddUserRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.addUser(request));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return service.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id){
        service.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "User Deleted Successfully"
        ));
    }
}
