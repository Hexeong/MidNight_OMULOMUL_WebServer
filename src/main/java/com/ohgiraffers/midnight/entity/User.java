package com.ohgiraffers.midnight.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "user")
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "USER_NAME", unique = true)
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "SHITBALLOON_SCORE")
    private int shitBalloonScore;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "MODIFIED_AT")
    private LocalDateTime modifiedAt;

    public User() {}

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, int shitBalloonScore, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.userName = userName;
        this.password = password;
        this.shitBalloonScore = shitBalloonScore;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getShitBalloonScore() {
        return shitBalloonScore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
