package com.phegondev.usersmanagementsystem.entity;




import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id") // Explicit foreign key column
    private OurUsers commentedBy;

    @ManyToOne
    @JoinColumn(name = "post_id") // Explicit foreign key column
    private Post post;

    private LocalDateTime timestamp; // Timestamp when the comment was created

    public Comment() {
        // Default constructor, initialize timestamp
        this.timestamp = LocalDateTime.now();
    }

    public Comment(String content, Post post, OurUsers commentedBy) {
        this.content = content;
        this.post = post;
        this.commentedBy = commentedBy;
        this.timestamp = LocalDateTime.now(); // Set timestamp on creation
    }
}






