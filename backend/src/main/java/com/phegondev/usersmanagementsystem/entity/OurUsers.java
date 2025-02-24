package com.phegondev.usersmanagementsystem.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "ourusers")
@Data
public class OurUsers implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String email;
    private String name;
    private String password;
    private Role role;
    private String about;


    @OneToMany(mappedBy = "ourUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    // Relationship for Following and Followers
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Follow> following = new HashSet<>();

    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Follow> followers = new HashSet<>();

    // Relationship for Likes
    @OneToMany(mappedBy = "ourUsers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Like> likedPosts = new HashSet<>();

    @OneToMany(mappedBy = "commentedBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // New method to like a post
    public void likePost(Post post) {
        Like like = new Like(this, post);
        this.likedPosts.add(like);
        post.getLikes().add(like);
    }

    // New method to remove a like
    public void unlikePost(Post post) {
        Like like = new Like(this, post);
        this.likedPosts.remove(like);
        post.getLikes().remove(like);
    }

    // Getters and Setters
    public Set<Like> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(Set<Like> likedPosts) {
        this.likedPosts = likedPosts;
    }
}
