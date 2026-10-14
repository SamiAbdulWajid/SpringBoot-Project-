package com.example.socialmedia.service;

import com.example.socialmedia.dto.PostDTO;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.exception.GlobalExceptionHandler;
import com.example.socialmedia.exception.UserNotFoundException;
import com.example.socialmedia.repo.PostRepository;
import com.example.socialmedia.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    @Autowired UserRepository userRepository;

    public PostDTO createPost(Post post){
        if(post == null) throw new UserNotFoundException("Post Not Found :(");

        Post savedPost = repository.save(post);

        return getBuild(savedPost);
    }

    public PostDTO getPostById(Long id){
        Post post=repository.findById(id).orElseThrow(()->new UserNotFoundException("Post not Found :("));

        return getBuild(post);
    } 

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<PostDTO> getPostsByUserId(Long id){

        if(userRepository.existsById(id)==false){
            throw new RuntimeException("Id don't exists "+ id);
        }

        List<Post> posts=repository.findByUserId(id);

        return posts.stream().map(post -> getBuild(post))
                .toList();
    }

    private static PostDTO getBuild(Post post) {
            return PostDTO.builder()
                    .id(post.getId())
                    .content(post.getContent())
                    .build();
    }

}
