package com.example.provider.security;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.core.security.Role;
import com.example.exception.CustomAuthenticationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
	
	private final JwtAuthTokenProvider jwtAuthTokenProvider;
	private static final String AUTHORIZATION_HEADER = "x-auth-token";
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		// TODO Auto-generated method stub
		
		log.info("AuthInterceptor preHandle");
		
		String token = resolveToken(request).orElseThrow(CustomAuthenticationException::new);
		JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token);
		if(jwtAuthToken.validate() && Role.USER.getCode().equals(jwtAuthToken.getData().get("role"))) {
			return true;
		}		
		
		throw new CustomAuthenticationException();
	}
	
	private Optional<String> resolveToken(HttpServletRequest request) {
		String authToken = request.getHeader(AUTHORIZATION_HEADER);
		
		if(StringUtils.hasText(authToken)) {
			return Optional.of(authToken);
		}
		
		return Optional.empty();
	}
}
