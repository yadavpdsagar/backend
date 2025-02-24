package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.entity.Category;
import com.phegondev.usersmanagementsystem.entity.OurUsers;
import com.phegondev.usersmanagementsystem.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByOurUser(OurUsers OurUsers);
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);
	

}
