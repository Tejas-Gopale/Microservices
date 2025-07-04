package com.practice.ecommerce.inventory_service.controller;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.practice.ecommerce.inventory_service.clients.OrderFeignClients;
import com.practice.ecommerce.inventory_service.dto.OrderRequestDto;
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
	private final DiscoveryClient discoveryClient;
	private final RestClient restClient;
	
	private final OrderFeignClients orderFeignClients;
	
	@GetMapping("/fetchOrders")
	public String fetchFromOrderService() {
	//	ServiceInstance orderService = discoveryClient.getInstances("order-service").get(0);
//		Rest Client Method
//		return	restClient.get()
//		.uri(orderService.getUri()+"/orders/core/helloOrders")
//		.retrieve()
//		.body(String.class);

		return orderFeignClients.helloOrders();
		
	}
		
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllInventory(){
		List<ProductDto> invenotries = productService.getAllInventory();
		return ResponseEntity.ok(invenotries);
	}
	
	@GetMapping("/{id}")
	 public ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id){
		ProductDto invenotory = productService.getProductById(id);
		return ResponseEntity.ok(invenotory);
	}
	
	@PutMapping("/reduce-stocks")
	public ResponseEntity<Double> reduceStocks(@RequestBody OrderRequestDto orderRequest){
		Double totalPrice = productService.reduceStocks(orderRequest);
		return ResponseEntity.ok(totalPrice);
	}
	
	@PutMapping("/increase-stock")
	public ResponseEntity<Double> increaseStock(@RequestBody OrderRequestDto orderRequest){
		Double totalPrice = productService.increaseStock(orderRequest);
		return ResponseEntity.ok(totalPrice);
	}
}
