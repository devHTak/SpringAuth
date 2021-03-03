package com.example.provider.security;

import java.security.Key;
import java.util.Date;

import com.example.core.security.AuthToken;

import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthToken implements AuthToken<Claims>{

	@Getter
	private final String token;
	private final Key key;
	private static final String AUTHORITY_KEY="role";
	
	JwtAuthToken(String token, Key key) {
		this.token = token; this.key = key;
	}
	
	JwtAuthToken(String id, String role, Date expiredDate, Key key) {
		this.key = key;
		this.token = createJwtAuthToken(id, role, expiredDate);
	}
	
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return this.getData() != null;
	}

	@Override
	public Claims getData() {
		// TODO Auto-generated method stub
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		} catch (SecurityException e) {
            log.info("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
        }
        return null;
	}
	
	private String createJwtAuthToken(String id, String role, Date expiredDate) {
		return Jwts.builder()
				.setSubject(id)
				.claim(AUTHORITY_KEY, key)
				.signWith(key, SignatureAlgorithm.HS256)
				.setExpiration(expiredDate)
				.compact();
	}
	
}
