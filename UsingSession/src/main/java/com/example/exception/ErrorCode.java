package com.example.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	
	AUTHENTICATION_FAILED(401, "AUTH_001", "AUTHENTICATION_FAILED."),
	LOGIN_FAILED(401, "AUTH_002", "LOGIN_FAILED");
	
	private final String code;
	private final String message;
	private final int status;
	
	ErrorCode(final int status, final String code, final String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

}
