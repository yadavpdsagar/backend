package com.phegondev.usersmanagementsystem.service.appService;

import com.phegondev.usersmanagementsystem.dto.CommentDto;
import com.phegondev.usersmanagementsystem.entity.Comment;
import com.phegondev.usersmanagementsystem.entity.Post;
import com.phegondev.usersmanagementsystem.exceptions.ResourceNotFoundException;
import com.phegondev.usersmanagementsystem.repository.CommentRepo;
import com.phegondev.usersmanagementsystem.repository.PostRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {
	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;


	public String uploadImage(String path, MultipartFile file) throws IOException {

		// File name
		String name = file.getOriginalFilename();
		// abc.png

		// random name generate file
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

		// Full path
		String filePath = path + File.separator + fileName1;

		// create folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// file copy

		Files.copy(file.getInputStream(), Paths.get(filePath));

		return fileName1;
	}

	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		// db logic to return inpustream
		return is;
	}

	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// Find the Post entity
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		// Create the Comment entity and copy properties from CommentDto to Comment using BeanUtils
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentDto, comment);  // Copy properties from CommentDto to Comment

		// Set the associated Post
		comment.setPost(post);

		// Save the Comment entity
		Comment savedComment = this.commentRepo.save(comment);

		// Create a new CommentDto to hold the saved Comment data
		CommentDto savedCommentDto = new CommentDto();
		BeanUtils.copyProperties(savedComment, savedCommentDto);  // Copy properties from Comment to CommentDto

		return savedCommentDto;
	}


	public void deleteComment(Integer commentId) {

		Comment com = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));


		this.commentRepo.delete(com);
	}


}
