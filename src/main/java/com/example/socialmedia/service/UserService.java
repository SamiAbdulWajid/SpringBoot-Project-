package com.example.socialmedia.service;

import java.util.List;

import com.example.socialmedia.dto.AddUserRequest;
import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.exception.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialmedia.entity.User;
import com.example.socialmedia.exception.UserNotFoundException;
import com.example.socialmedia.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> getAllUser() {
        List<User> allUsers = repository.findAll();
        return allUsers.stream().map(
                (user) -> mapUserEntityToDto(user)).toList();

    }

    public UserDTO findByUsername(String username) {
        User user = repository.findByUsername(username);
        return mapUserEntityToDto(user);
    }

    private static UserDTO mapUserEntityToDto(User user) {
        UserDTO userDTO = UserDTO.builder()
                .age(user.getAge())
                .username(user.getUsername())
                .build();
        return userDTO;
    }

    public UserDTO findUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
        return mapUserEntityToDto(user);
    }

    public UserDTO addUser(AddUserRequest request) {
        if (repository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        ;

        User user = new User();
        user.setUsername(request.getUsername());
        user.setAge(request.getAge());
        user.setPassword(request.getPassword());

        return mapUserEntityToDto(repository.save(user));
    }

    public User updateUser(Long id, User user) {
        User existingUser = repository.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setAge(user.getAge());
            return existingUser;
        }

        return null;
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
