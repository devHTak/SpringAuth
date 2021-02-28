package com.example.web;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.CoffeeDTO;
import com.example.provider.CoffeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CoffeeController {
	
	private final CoffeeService coffeeService;
	
	@GetMapping("/api/v1/coffees")
	public List<CoffeeDTO> getAllCoffess(HttpSession session) {
		log.info("세션 아이디: " + session.getId());
		log.info("이메일주소: " + String.valueOf(session.getAttribute("email")));
		log.info("권한: " + String.valueOf(session.getAttribute("role")));
		
		return coffeeService.findAll().orElse(Collections.emptyList());
	}

}
