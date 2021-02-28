package com.example.provider;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.core.LoginUseCase;
import com.example.core.MemberDTO;
import com.example.core.Role;

@Service
public class LoginService implements LoginUseCase {

	@Override
	public Optional<MemberDTO> login(String email, String password) {
		// TODO Auto-generated method stub
		
		// TODO Login 연동 -> UserDetailsService
		
		MemberDTO member = MemberDTO.builder()
				.username("test")
				.email(email)
				.role(Role.USER)
				.build();
		
		return Optional.of(member);
	}
	
	

}
