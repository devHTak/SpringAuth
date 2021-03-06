package com.example.provider.security;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.core.security.AuthTokenProvider;
import com.example.exception.TokenValidFailedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthTokenProvider implements AuthTokenProvider<JwtAuthToken> {
	
	private final Key key;
	private static final String AUTHORITIES_KEY = "role";
	
	public JwtAuthTokenProvider(String base64Secret) {
		byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
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

	@Override
	public Authentication getAuthentication(JwtAuthToken authToken) {
		// TODO Auto-generated method stub
		
		if(authToken.validate()) {
			Claims claims = authToken.getData();
			
			Collection<? extends GrantedAuthority> authorities = 
					Arrays.stream(new String[] {claims.get(AUTHORITIES_KEY).toString()})
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
			User principal = new User(claims.getSubject(), "", authorities);
			
			return new UsernamePasswordAuthenticationToken(principal, claims, authorities);
		}
		
		throw new TokenValidFailedException();
	}
}
