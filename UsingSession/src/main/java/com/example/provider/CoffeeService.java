package com.example.provider;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.core.CoffeeDTO;
import com.example.core.CoffeeUseCase;

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
