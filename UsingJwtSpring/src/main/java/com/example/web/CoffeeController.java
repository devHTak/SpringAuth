package com.example.web;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.service.dto.CoffeeDTO;
import com.example.provider.service.CoffeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CoffeeController {
	
	private final CoffeeService coffeeService;
	
	@GetMapping("/api/v1/coffees")
	public List<CoffeeDTO> getAllCoffess() {
		return coffeeService.findAll().orElse(Collections.emptyList());
	}

}
