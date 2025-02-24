package com.phegondev.usersmanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private OurUsers ourUsers; // The user who liked the post

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post; // The post that was liked

    public Like() {
    }

    public Like(OurUsers user, Post post) {
        this.ourUsers = user;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OurUsers getUser() {
        return ourUsers;
    }

    public void setUser(OurUsers user) {
        this.ourUsers = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

