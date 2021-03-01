package com.example.provider.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.core.service.CoffeeUseCase;
import com.example.core.service.dto.CoffeeDTO;

@Service
public class CoffeeService implements CoffeeUseCase {

	@Override
	public Optional<List<CoffeeDTO>> findAll() {
		// TODO Auto-generated method stub
		CoffeeDTO latte = CoffeeDTO.builder()
				.name("Latte")
				.price(1200).build();
		
		CoffeeDTO americano = CoffeeDTO.builder()
				.name("Americano")
				.price(1200).build();
		
		return Optional.of(
				Arrays.asList(latte, americano));
	}
	
	
	
	

}
