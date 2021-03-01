package com.example.provider.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.core.security.AuthToken;
import com.example.core.security.Role;
import com.example.core.service.LoginUseCase;
import com.example.core.service.dto.MemberDTO;
import com.example.provider.security.JwtAuthTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {
	
	private final JwtAuthTokenProvider provider;
	private final static Long LOGIN_RETENTION_MINUTES = 30L;

	@Override
	public Optional<MemberDTO> login(String email, String password) {
		// TODO Auto-generated method stub		
		// TODO Login 연동		
		MemberDTO member = MemberDTO.builder()
				.username("test")
				.email(email)
				.role(Role.USER)
				.build();
		
		return Optional.of(member);
	}

	@Override
	public AuthToken createAuthToken(MemberDTO member) {
		// TODO Auto-generated method stub
		ZonedDateTime datePlusMinutes = LocalDateTime.now().plusMinutes(LOGIN_RETENTION_MINUTES).atZone(ZoneId.systemDefault());
		Date expiredDate = Date.from(datePlusMinutes.toInstant());
				
		return provider.createAuthToken(member.getId(), member.getRole().getCode(), expiredDate);
	}
	
}
