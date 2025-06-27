package com.practice.ecommerce.inventory_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.ecommerce.inventory_service.dto.ProductDto;
import com.practice.ecommerce.inventory_service.service.ProductService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService; 
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllInventory(){
		List<ProductDto> invenotries = productService.getAllInventory();
		return ResponseEntity.ok(invenotries);
	}
	
	@GetMapping("/id")
	 public ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id){
		ProductDto invenotory = productService.getProductById(id);
		return ResponseEntity.ok(invenotory);
	}
}
