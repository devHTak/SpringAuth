package com.example.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import com.example.core.service.dto.MemberDTO;
import com.example.exception.LoginFailedException;
import com.example.provider.security.JwtAuthToken;
import com.example.provider.service.LoginService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CoffeeControllerTest {
	
	@Autowired MockMvc mockMvc;
	@Autowired LoginService loginService;
	
	private static final String AUTHORIZATION_HEADER = "x-auth-token";
	private String loginToken;
	
	@BeforeEach
	void beforeEach() throws Exception {
		MemberDTO member = loginService.login("test@test.com", "test1234")
				.orElseThrow(LoginFailedException::new);
		JwtAuthToken token = (JwtAuthToken)loginService.createAuthToken(member);
		this.loginToken = token.getToken();
	}
	
	@Test
	void coffeeTest() throws Exception {
		mockMvc.perform(get("/api/v1/coffees")
				.header(AUTHORIZATION_HEADER, loginToken))
			.andDo(print())
			.andExpect(status().isOk());
	}

}
