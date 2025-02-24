package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.entity.Like;
import com.phegondev.usersmanagementsystem.entity.OurUsers;
import com.phegondev.usersmanagementsystem.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like , Integer> {



}
