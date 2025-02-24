package com.phegondev.usersmanagementsystem.service.appService;



import com.phegondev.usersmanagementsystem.dto.PostDto;
import com.phegondev.usersmanagementsystem.entity.Category;
import com.phegondev.usersmanagementsystem.entity.OurUsers;
import com.phegondev.usersmanagementsystem.entity.Post;
import com.phegondev.usersmanagementsystem.exceptions.ResourceNotFoundException;
import com.phegondev.usersmanagementsystem.payloads.PostResponse;
import com.phegondev.usersmanagementsystem.repository.CategoryRepo;
import com.phegondev.usersmanagementsystem.repository.PostRepo;
import com.phegondev.usersmanagementsystem.repository.UsersRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UsersRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	 
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		OurUsers  user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", categoryId));

		// Create a new Post object
		Post post = new Post();

		// Copy properties from PostDto to Post
		BeanUtils.copyProperties(postDto, post);

		// Additional properties
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setOurUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);

		// Prepare and return the PostDto
		PostDto postDtoResponse = new PostDto();
		BeanUtils.copyProperties(newPost, postDtoResponse);
		return postDtoResponse;
	}

	 
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));

		Category category = this.categoryRepo.findById(postDto.getCategory().getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", postDto.getCategory().getCategoryId()));

		// Copy properties from PostDto to Post
		BeanUtils.copyProperties(postDto, post);

		// Set the category and update the post
		post.setCategory(category);

		Post updatedPost = this.postRepo.save(post);

		// Prepare and return the updated PostDto
		PostDto updatedPostDto = new PostDto();
		BeanUtils.copyProperties(updatedPost, updatedPostDto);
		return updatedPostDto;
	}

	 
	public void deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));

		this.postRepo.delete(post);
	}

	 
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = this.postRepo.findAll(p);

		List<Post> allPosts = pagePost.getContent();

		List<PostDto> postDtos = allPosts.stream()
				.map(post -> {
					PostDto postDto = new PostDto();
					BeanUtils.copyProperties(post, postDto);
					return postDto;
				})
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	 
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		PostDto postDto = new PostDto();
		BeanUtils.copyProperties(post, postDto);
		return postDto;
	}

	 
	public List<PostDto> getPostsByCategory(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		List<Post> posts = this.postRepo.findByCategory(category);

		return posts.stream()
				.map(post -> {
					PostDto postDto = new PostDto();
					BeanUtils.copyProperties(post, postDto);
					return postDto;
				})
				.collect(Collectors.toList());
	}

	 
	public List<PostDto> getPostsByUser(Integer userId) {

		OurUsers user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "userId", userId));

		List<Post> posts = this.postRepo.findByOurUser(user);

		return posts.stream()
				.map(post -> {
					PostDto postDto = new PostDto();
					BeanUtils.copyProperties(post, postDto);
					return postDto;
				})
				.collect(Collectors.toList());
	}

	 
	public List<PostDto> searchPosts(String keyword) {

		List<Post> posts = this.postRepo.searchByTitle("%" + keyword + "%");

		return posts.stream()
				.map(post -> {
					PostDto postDto = new PostDto();
					BeanUtils.copyProperties(post, postDto);
					return postDto;
				})
				.collect(Collectors.toList());
	}

}
