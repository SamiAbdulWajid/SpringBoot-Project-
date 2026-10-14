package com.example.socialmedia.service;

import com.example.socialmedia.dto.FollowDTO;
import com.example.socialmedia.entity.Follow;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.exception.UserNotFoundException;
import com.example.socialmedia.repo.FollowRepository;
import com.example.socialmedia.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {
    @Autowired
    private FollowRepository repository;

    @Autowired 
    private UserRepository userRepository;
    //API-Follow a User
    public void followUser(Follow follow){
        repository.save(follow);
    }

    public void unfollowUser(Long followerId,Long followingId){
        repository.deleteByFollower_IdAndFollowing_Id(followerId,followingId);
    }

    public List<FollowDTO> getFollowersInfo(Long followingId) {
            if (!userRepository.existsById(followingId)) {
                throw new UserNotFoundException(
                        "User doesn't exist: " + followingId);
            }

            List<Follow> followers =
                    repository.findByfollowing_Id(followingId);

            return followers.stream()
                    .map(follow -> FollowDTO.builder()
                            .id(follow.getId())
                            .followerId(follow.getFollower().getId())
                            .followerUsername(follow.getFollower().getUsername())
                            .followingId(follow.getFollowing().getId())
                            .followingUsername(follow.getFollowing().getUsername())
                            .build())
                    .toList();
        }

    public List<FollowDTO> getFollowerFollowings(Long followerId) {
        if (!userRepository.existsById(followerId)) {
            throw new UserNotFoundException(
                    "User doesn't exist: " + followerId);
        }

        List<Follow> following =
                repository.findByFollower_Id(followerId);

        return following.stream()
                .map(follow -> FollowDTO.builder()
                        .id(follow.getId())
                        .followerId(follow.getFollower().getId())
                        .followerUsername(follow.getFollower().getUsername())
                        .followingId(follow.getFollowing().getId())
                        .followingUsername(follow.getFollowing().getUsername())
                        .build())
                .toList();
    }
}
