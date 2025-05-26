package com.example.schedulemanagement.schedules;

import com.example.schedulemanagement.utils.BaseEntity;
import com.example.schedulemanagement.users.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedules")
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Todo() {}
    public Todo(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }
}
