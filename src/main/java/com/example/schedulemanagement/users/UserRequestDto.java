package com.example.schedulemanagement.users;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotNull
    @Size(min = 2, max = 30)
    private final String name;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "check your email")
    @Size(min = 10, max = 50)
    private final String email;

    @NotBlank
    @Size(min = 4, max = 30)
    private final String password;

    public UserRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}