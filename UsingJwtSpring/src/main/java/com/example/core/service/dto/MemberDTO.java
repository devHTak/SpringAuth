package com.example.core.service.dto;

import com.example.core.security.Role;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MemberDTO {
	
	private String id;
	private String username;
	private String email;
	private Role role;

}
