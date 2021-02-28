package com.example.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.example.core.Role;
import com.example.core.SecurityConstants;
import com.example.web.dto.LoginRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class LoginControllerTest {
	
	@Autowired MockMvc mockMvc;
	@Autowired MockHttpSession session;
	@Autowired ObjectMapper objectMapper;
	
	@Test
	void loginTest() throws Exception {
		LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
				.email("test@test.com").password("test1234").build();
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/login")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(loginRequestDTO))
				.session(session);
		
		mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().isOk());
		
		assertEquals(session.getAttribute("email"), "test@test.com");
	}

}
