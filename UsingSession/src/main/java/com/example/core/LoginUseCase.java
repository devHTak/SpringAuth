package com.example.core;

import java.util.Optional;

public interface LoginUseCase {
	Optional<MemberDTO> login(String email, String password);
}
