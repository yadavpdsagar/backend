package com.phegondev.usersmanagementsystem.entity;


import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name="title",length = 100,nullable = false)
    private String categoryTitle;

    @Column(name="description")
    private String categoryDescription;


    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();


    public Integer getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    public String getCategoryTitle() {
        return categoryTitle;
    }


    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }


    public String getCategoryDescription() {
        return categoryDescription;
    }


    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }


    public List<Post> getPosts() {
        return posts;
    }


    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }



}
