package com.example.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.core.CoffeeDTO;
import com.example.core.MemberDTO;
import com.example.core.SecurityConstants;
import com.example.exception.LoginFailedException;
import com.example.provider.CoffeeService;
import com.example.provider.LoginService;
import com.example.web.dto.LoginRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CoffeeControllerTest {
	
	@Autowired MockMvc mockMvc;
	@Autowired CoffeeService coffeeService;
	@Autowired LoginService loginService;
	@Autowired MockHttpSession session;
	@Autowired ObjectMapper objectMapper;
	
	@BeforeEach
	void beforeEach() throws Exception {
		MemberDTO member = loginService.login("test@test.com", "test1234")
				.orElseThrow(LoginFailedException::new);
		
		session.setAttribute(SecurityConstants.KEY_ROLE, member.getRole().name());
		session.setAttribute("email", member.getEmail());
		session.setMaxInactiveInterval(1800);
	}
	
	@Test
	void getAllCoffessTest() throws Exception {
		MockHttpServletRequestBuilder builder = this.getRequestBuilder("/api/v1/coffess");
		
		mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().isOk());
		
		List<CoffeeDTO> coffess = coffeeService.findAll().orElse(Collections.emptyList());
		
		assertNotNull(coffess);
		assertEquals("Latte", coffess.get(0).getName());
		assertEquals("Americano", coffess.get(1).getName());
	}
	
	private MockHttpServletRequestBuilder getRequestBuilder(String uri) {
		return MockMvcRequestBuilders.get(uri).session(session);
	}

}
