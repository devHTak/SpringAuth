package com.example.core.service;

import java.util.List;
import java.util.Optional;

import com.example.core.service.dto.CoffeeDTO;

public interface CoffeeUseCase {
	
	Optional<List<CoffeeDTO>> findAll();

}
