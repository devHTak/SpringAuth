package com.example.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Getter;

@Getter @Entity
public class Member {
	
	@Id @GeneratedValue @JsonIgnore
	private Long id;
	
	@NotNull @Size(min = 4, max = 50)
	private String username;
	
	@NotNull @Size(min = 4, max = 100)
	private String password;
	
	@NotNull @Size(min = 4, max = 50)
	private String email;
	
	@NotNull @Size(min = 4, max = 50)
	private String role;
	

}
