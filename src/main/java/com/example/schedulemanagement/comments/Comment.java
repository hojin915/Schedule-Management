package com.example.schedulemanagement.comments;

import com.example.schedulemanagement.BaseEntity;
import com.example.schedulemanagement.schedules.Todo;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String contents;

    public Comment() {}
    public Comment(String contents) {
        this.contents = contents;
    }

    public void setTodo(Todo todo){
        this.todo = todo;
        this.userId = todo.getUser().getId();
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }
}
