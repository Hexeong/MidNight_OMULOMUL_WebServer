package com.ohgiraffers.midnight.repository;

import com.ohgiraffers.midnight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserName(String username);
}
