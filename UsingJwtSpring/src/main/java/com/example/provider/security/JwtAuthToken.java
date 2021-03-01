package com.example.provider.security;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import com.example.core.security.AuthToken;
import com.example.exception.CustomJwtRuntimeException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
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
		return getData() != null;
	}

	@Override
	public Claims getData() {
		// TODO Auto-generated method stub
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		} catch(SecurityException e) {
			log.info("Invalid JWT signature");
			throw new CustomJwtRuntimeException();
		} catch(MalformedJwtException e) {
			log.info("Invalid JWT token");
			throw new CustomJwtRuntimeException();
		} catch(ExpiredJwtException e) {
			log.info("Expired JWT token");
			throw new CustomJwtRuntimeException();
		} catch(UnsupportedJwtException e) {
			log.info("Unsupported JWT token");
			throw new CustomJwtRuntimeException();
		} catch(IllegalArgumentException e) {
			log.info("Jwt token compact of handler are invalid.");
			throw new CustomJwtRuntimeException();
		}
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
