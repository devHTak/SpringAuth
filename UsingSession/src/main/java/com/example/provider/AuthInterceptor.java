package com.example.provider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.core.Role;
import com.example.core.SecurityConstants;
import com.example.exception.CustomAuthenticationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		log.info("AuthInterceptor preHandle");
		System.out.println("A");
		if(request.getSession().getAttribute(SecurityConstants.KEY_ROLE) != null
				&& request.getSession().getAttribute(SecurityConstants.KEY_ROLE).equals(Role.USER.name())) {
			return true;
		}
		
		throw new CustomAuthenticationException();
	}
}
