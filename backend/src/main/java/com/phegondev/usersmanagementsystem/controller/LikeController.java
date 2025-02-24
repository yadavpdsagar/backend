package com.phegondev.usersmanagementsystem.controller;
import com.phegondev.usersmanagementsystem.entity.OurUsers;
import com.phegondev.usersmanagementsystem.service.appService.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminuser/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/like/{postId}")
    public String likePost(@PathVariable Integer postId, Authentication authentication) {
        OurUsers user = (OurUsers) authentication.getPrincipal(); // Get authenticated user
        likeService.likePost(user, postId);
        return "Post liked successfully!";
    }


}

