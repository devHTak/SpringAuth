package com.example.provider.security;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import com.example.core.security.AuthToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class JwtAuthToken implements AuthToken<Claims>{
	
	private final String token;
	private final Key key;
	
	private static final String AUTHORITIES_KEY = "role";
		
	public JwtAuthToken(String token, Key key) {
		this.token = token;
		this.key = key;
	}
	
	public JwtAuthToken(String id, String role, Date expiredDate, Key key) {
		this.key = key;
		this.token = createJwtAuthToken(id, role, expiredDate).get();
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Claims getData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Optional<String> createJwtAuthToken(String id, String role, Date expiredDate) {
		String token = Jwts.builder()
				.setSubject(id)
				.claim(AUTHORITIES_KEY, role)
				.signWith(key, SignatureAlgorithm.HS256)
				.setExpiration(expiredDate)
				.compact();
		
		return Optional.of(token);
	}
	
	

}
