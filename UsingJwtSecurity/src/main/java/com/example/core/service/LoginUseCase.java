package com.example.core.service;

import java.util.Optional;

import com.example.core.security.AuthToken;
import com.example.core.service.dto.MemberDTO;

public interface LoginUseCase {
	
	Optional<MemberDTO> login(String email, String password);
	
	AuthToken createAuthToken(MemberDTO memberDTO);

}
