package com.example.provider.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.core.service.CoffeeUseCase;
import com.example.core.service.dto.CoffeeDTO;

public class CoffeeService implements CoffeeUseCase {

	@Override
	public Optional<List<CoffeeDTO>> findAll() {
		// TODO Auto-generated method stub
		CoffeeDTO americano = CoffeeDTO.builder().name("americano").price(4200).build();
		CoffeeDTO latte = CoffeeDTO.builder().name("latte").price(4600).build();
		return Optional.of(Arrays.asList(americano, latte));
	}
	
	

}
