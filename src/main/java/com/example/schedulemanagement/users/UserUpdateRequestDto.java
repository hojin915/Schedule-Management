package com.example.schedulemanagement.users;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private final String name;
    private final String email;
    private final String password;

    public UserUpdateRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
