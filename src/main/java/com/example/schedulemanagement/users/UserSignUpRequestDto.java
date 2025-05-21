package com.example.schedulemanagement.users;

import lombok.Getter;

@Getter
public class UserSignUpRequestDto {
    private final String name;
    private final String email;
    private final String password;

    public UserSignUpRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
