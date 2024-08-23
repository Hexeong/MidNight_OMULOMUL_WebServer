package com.ohgiraffers.midnight.repository;

import com.ohgiraffers.midnight.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    Result findResultByResultNo(int resultNo);
    List<Result> findResultsByUserName(String userName);
    @Query("SELECT r FROM Result r ORDER BY r.createdAt DESC limit 1")
    Result findTopByOrderByCreatedAtDesc();
}
