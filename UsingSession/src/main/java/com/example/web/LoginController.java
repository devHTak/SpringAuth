package com.example.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.MemberDTO;
import com.example.core.SecurityConstants;
import com.example.exception.LoginFailedException;
import com.example.provider.LoginService;
import com.example.web.dto.LoginRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
	private final LoginService loginService;
	@PostMapping("/api/v1/login")
	public String login(HttpSession session, @RequestBody LoginRequestDTO loginRequestDTO) {
		MemberDTO member = loginService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
				.orElseThrow(LoginFailedException::new);
		
		session.setAttribute(SecurityConstants.KEY_ROLE, member.getRole().name());
		session.setAttribute("email", member.getEmail());
		session.setMaxInactiveInterval(1800);
		
		return "ok";
	}
}
