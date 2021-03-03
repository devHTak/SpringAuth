package com.example.core.service.dto;

import com.example.core.entity.Member;
import com.example.core.security.Role;

import lombok.Builder;

import lombok.Getter;

@Getter @Builder 
public class MemberDTO {
	
	private Long id;
	private String username;
	private String email;
	private Role role;
	
	public static MemberDTO of(Member member) {
		return MemberDTO.builder()
				.id(member.getId())
				.username(member.getUsername())
				.email(member.getEmail())
				.role(Role.of(member.getRole())).build();
	}

}
