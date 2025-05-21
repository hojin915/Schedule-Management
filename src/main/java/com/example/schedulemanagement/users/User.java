package com.example.schedulemanagement.users;

import com.example.schedulemanagement.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Email(message = "required email type")
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public User() {}
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void updateEmail(String email){
        this.email = email;
    }
}