package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.provider.security.JwtAuthTokenProvider;

@Configuration
public class JwtConfiguration {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Bean
	public JwtAuthTokenProvider jwtProvider() {
		return new JwtAuthTokenProvider(secret);
	}

}
