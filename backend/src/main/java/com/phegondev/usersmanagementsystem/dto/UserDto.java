package com.phegondev.usersmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.phegondev.usersmanagementsystem.dto.RoleDto;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 characters !!")
	private String name;

	@Email(message = "Email address is not valid !!")
	@NotEmpty(message = "Email is required !!")
	private String email;

	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars !!")


	private String password;

	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	 

}
