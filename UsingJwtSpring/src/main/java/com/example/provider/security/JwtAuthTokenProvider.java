package com.example.provider.security;

import java.security.Key;
import java.util.Date;

import com.example.core.security.AuthTokenProvider;

import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthTokenProvider implements AuthTokenProvider<JwtAuthToken> {
	
	private final Key key;
	
	public JwtAuthTokenProvider(String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}

	@Override
	public JwtAuthToken createAuthToken(String id, String role, Date expiredDate) {
		// TODO Auto-generated method stub
		return new JwtAuthToken(id, role, expiredDate, this.key);
	}

	@Override
	public JwtAuthToken convertAuthToken(String token) {
		// TODO Auto-generated method stub
		return new JwtAuthToken(token, this.key);
	}
}
