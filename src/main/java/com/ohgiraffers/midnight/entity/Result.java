package com.ohgiraffers.midnight.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "Result")
@Table(name = "tbl_result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESULT_NO")
    private int resultNo;

    @Column(name = "score")
    private int score;

    @Column(name = "CAUSE")
    private String cause;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    public Result() {
    }

    public Result(int score, String cause, String userName) {
        this.score = score;
        this.cause = cause;
        this.userName = userName;
    }

    public Result(int score, String cause, LocalDateTime createdAt, String userName) {
        this.score = score;
        this.cause = cause;
        this.createdAt = createdAt;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultNo=" + resultNo +
                ", score=" + score +
                ", cause='" + cause + '\'' +
                ", createdAt=" + createdAt +
                ", userName='" + userName + '\'' +
                '}';
    }

    public int getResultNo() {
        return resultNo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
