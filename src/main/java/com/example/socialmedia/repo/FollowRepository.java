package com.example.socialmedia.repo;

import com.example.socialmedia.entity.Follow;
import com.example.socialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {
 void deleteByFollower_IdAndFollowing_Id(Long followerId,Long followingId);

 List<Follow> findByfollowing_Id(Long followingId);

 List<Follow> findByFollower_Id(Long followerId);
}
