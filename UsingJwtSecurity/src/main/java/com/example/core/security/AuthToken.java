package com.example.core.security;

public interface AuthToken<T> {
	
	boolean validate();
	T getData();

}
