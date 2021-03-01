package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.core.CommonResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomAuthenticationException.class)
	protected ResponseEntity<CommonResponse> handleCustomAuthenticationException(CustomAuthenticationException e) {
		log.info("CustomAuthenticationException", e);
		CommonResponse response = CommonResponse.builder()
				.code(ErrorCode.AUTHENTICATION_FAILED.getCode())
				.message(e.getMessage())
				.status(ErrorCode.AUTHENTICATION_FAILED.getStatus()).build();
		
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<CommonResponse> handleLoginFailedException(LoginFailedException e) {
		log.info("LoginFailedException", e);
		
		CommonResponse response = CommonResponse.builder()
				.code(ErrorCode.LOGIN_FAILED.getCode())
				.message(e.getMessage())
				.status(ErrorCode.LOGIN_FAILED.getStatus()).build();
		
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		
	}
	
	@ExceptionHandler(CustomJwtRuntimeException.class)
	public ResponseEntity<CommonResponse> handleCustomJwtRuntimeException(CustomJwtRuntimeException e) {
		log.info("CustomJwtRuntimeException", e);
		
		CommonResponse response = CommonResponse.builder()
				.code(ErrorCode.INVALID_JWT_TOKEN.getCode())
				.message(e.getMessage())
				.status(ErrorCode.INVALID_JWT_TOKEN.getStatus()).build();
		
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

}
