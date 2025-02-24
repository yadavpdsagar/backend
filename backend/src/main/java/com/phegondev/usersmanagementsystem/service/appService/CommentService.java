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

@Service
public class CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;


	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// Find the Post entity
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		// Create the Comment entity and copy properties from CommentDto to Comment using BeanUtils
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentDto, comment);  // Copy properties from CommentDto to Comment

		// Set the associated post
		comment.setPost(post);

		// Save the Comment entity
		Comment savedComment = this.commentRepo.save(comment);

		// Create a new CommentDto to hold the saved Comment data
		CommentDto savedCommentDto = new CommentDto();
		BeanUtils.copyProperties(savedComment, savedCommentDto);  // Copy properties from Comment to CommentDto

		return savedCommentDto;
	}


	public void deleteComment(Integer commentId) {
		// Find the Comment entity
		Comment com = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));

		// Delete the Comment entity
		this.commentRepo.delete(com);
	}


}
