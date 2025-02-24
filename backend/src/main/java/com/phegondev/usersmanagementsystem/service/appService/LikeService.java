package com.phegondev.usersmanagementsystem.service.appService;


import com.phegondev.usersmanagementsystem.entity.Post;
import com.phegondev.usersmanagementsystem.entity.OurUsers;
import com.phegondev.usersmanagementsystem.entity.Like;
import com.phegondev.usersmanagementsystem.repository.PostRepo;
import com.phegondev.usersmanagementsystem.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private PostRepo postRepository;

    @Autowired
    private LikeRepository likeRepository;

    public void likePost(OurUsers user, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        user.likePost(post);
        likeRepository.save(new Like(user, post));
    }


}

