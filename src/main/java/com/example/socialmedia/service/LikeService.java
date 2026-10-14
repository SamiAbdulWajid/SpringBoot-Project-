package com.example.socialmedia.service;

import com.example.socialmedia.repo.UserRepository;
import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.entity.Like;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.repo.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    //get all users of a post(yt_video) by the PostID poore users ku tariqe ke saath nikalre in UserDTO form
    public List<UserDTO> findUsersByPostId(Long postId) {
        List<Like> likes = likeRepository.findUsersByPostId(postId);

        return likes.stream() //poore userke 
                .map(like -> {
                    User user = userRepository.findById(like.getUserId()).get();
                    return UserDTO.builder()
                                .age(user.getAge())
                                .username(user.getUsername())
                                .build();
                })
                .toList();

    }

    public void unlikePost(Long userId,Long postId){
        likeRepository.deleteByUserIdAndPostId(userId,postId);
    }

    public void likePost(Like like){
                          //checkUserIdAndPostId(getuserId,getpostId.isPresent())
        if(likeRepository.findByUserIdAndPostId(like.getUserId(), like.getPostId()).isPresent()){
            throw new RuntimeException("User already liked the post");
        }
        likeRepository.save(like);
    }

}




