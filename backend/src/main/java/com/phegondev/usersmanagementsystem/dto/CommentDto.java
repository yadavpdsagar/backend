package com.phegondev.usersmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentDto {
	
	private int id;

	private String content;
	private UserDto commentedBy;
	private LocalDateTime timestamp;

}
