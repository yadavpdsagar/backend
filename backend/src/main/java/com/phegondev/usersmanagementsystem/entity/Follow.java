package com.phegondev.usersmanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "follows")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private OurUsers follower; // The user who is following

    @ManyToOne
    @JoinColumn(name = "followed_id")
    private OurUsers followed; // The user being followed

    public Follow() {
    }

    public Follow(OurUsers follower, OurUsers followed) {
        this.follower = follower;
        this.followed = followed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OurUsers getFollower() {
        return follower;
    }

    public void setFollower(OurUsers follower) {
        this.follower = follower;
    }

    public OurUsers getFollowed() {
        return followed;
    }

    public void setFollowed(OurUsers followed) {
        this.followed = followed;
    }
}
