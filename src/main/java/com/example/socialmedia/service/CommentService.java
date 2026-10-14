package com.example.socialmedia.service;

import com.example.socialmedia.entity.Comment;
import com.example.socialmedia.exception.UserNotFoundException;
import com.example.socialmedia.dto.CommentDTO;
import com.example.socialmedia.repo.CommentRespository;
import com.example.socialmedia.repo.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.management.RuntimeErrorException;

@Service
public class CommentService {

    @Autowired
    private CommentRespository repository;

    @Autowired 
    private PostRepository postRepository;

    public CommentDTO createComment(Comment comment){
        Comment savedComment=repository.save(comment);

        return mapToDTO(savedComment);
    }

    public String deleteById(Long id){
        repository.deleteById(id);
        return "Deleted Successfully , id";
    }

    public List<CommentDTO> getCommentsByPostId(Long postId){
        if(postRepository.existsById(postId)==false){
           throw new UserNotFoundException("Invalid post Id in get all comments of this particular postId");
        }
 
        List<Comment> comments=repository.findByPostId(postId);

        return comments.stream().map(comment -> mapToDTO(comment))
                .toList();

    }

    private static CommentDTO mapToDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .userId(comment.getUser().getId())
                .postId(comment.getPost().getId())
                .build();
    }
}
