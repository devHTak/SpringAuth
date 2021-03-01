package com.example.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.CommonResponse;
import com.example.core.service.dto.MemberDTO;
import com.example.exception.LoginFailedException;
import com.example.provider.security.JwtAuthToken;
import com.example.provider.service.LoginService;
import com.example.web.dto.LoginRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;
	
	@PostMapping("/api/v1/login")
	public CommonResponse login(@RequestBody LoginRequestDTO loginRequestDTO) {
		MemberDTO member = loginService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
				.orElseThrow(LoginFailedException::new);
		
		JwtAuthToken token = (JwtAuthToken)loginService.createAuthToken(member);
		return CommonResponse.builder()
				.code("LOGIN_SUCCESS")
				.status(200)
				.message(token.getToken())
				.build();
	}
}
