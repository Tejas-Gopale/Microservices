package com.practice.ecommerce.inventory_service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class ProductDto {
	
	private Long id;
	private String title; 
	
	private Double price;
	
	private Integer stock;
	
}
