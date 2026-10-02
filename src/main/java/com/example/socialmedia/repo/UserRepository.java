package com.example.socialmedia.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.socialmedia.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    //custom methods we are defining here
    User findByUsername(String username);

    boolean existsByUsername(String username);
}
