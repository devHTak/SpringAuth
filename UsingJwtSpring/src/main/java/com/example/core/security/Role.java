package com.example.core.security;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum Role {
	ADMIN("ROLE_ADMIN", "관리자 권한"), 
	USER("ROLE_USER", "사용자 권한"),
	UNKNOWN("UNKNOWN", "알수없는 권한");
	
	private String code;
	private String description;
	
	Role(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public static Role of(String code) {
		return Arrays.stream(Role.values())
				.filter(role -> role.getCode().equals(code))
				.findAny().orElse(UNKNOWN);
	}
}
