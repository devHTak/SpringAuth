package com.example.web.dto;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import lombok.Builder;

import lombok.Setter;

import lombok.Getter;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class LoginRequestDTO {
	
	private String email;
	private String password;

}
