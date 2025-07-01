package com.practice.ecommerce.inventory_service.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.practice.ecommerce.inventory_service.dto.OrderRequestDto;
import com.practice.ecommerce.inventory_service.dto.OrderRequestItemDto;
import com.practice.ecommerce.inventory_service.dto.ProductDto;
import com.practice.ecommerce.inventory_service.entity.Product;
import com.practice.ecommerce.inventory_service.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;
	
	// get all the inventory by the repository
	public List<ProductDto> getAllInventory(){
		log.info("feteching all inventory items");
		List<Product> inventories = productRepository.findAll();
		log.info(inventories.toString());
		return inventories.stream()
				.map(product -> modelMapper.map(product, ProductDto.class))
				.toList();
	}    
	
	//get product by id
	public ProductDto getProductById(Long id) {
		log.info("Feteching product with ID : {}",id);
		
		Optional<Product> inventory = productRepository.findById(id);
		return inventory.map(item -> modelMapper.map(inventory, ProductDto.class))
				.orElseThrow(() -> new RuntimeException("Invernoty not Found"));
	}

	@Transactional
	public Double reduceStocks(OrderRequestDto orderRequestDto) {
		log.info("Reducing the stocks ");
		Double totalPrice = 0.0;
		for(OrderRequestItemDto orderRequestItemDto : orderRequestDto.getItems()) {
			Long productId = orderRequestItemDto.getProductId();
			Integer quanity = orderRequestItemDto.getQuanity();
			
			Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product Not Found:"));
			
			if(product.getStock() < quanity) {
				throw new RuntimeException("Product out of Stock :"+ quanity);
			}
			
			product.setStock(product.getStock()-quanity);
			productRepository.save(product);
			totalPrice = quanity * product.getPrice();
			
			
		}
		return totalPrice;
	}
	
}
