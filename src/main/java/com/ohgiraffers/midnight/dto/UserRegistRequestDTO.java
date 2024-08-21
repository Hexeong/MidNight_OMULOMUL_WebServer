package com.ohgiraffers.midnight.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegistRequestDTO {
    String username;
    String password;
}
