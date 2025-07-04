package com.practice.ecommerce.order_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.ecommerce.order_service.clients.InventoryOpenFeignClient;
import com.practice.ecommerce.order_service.dto.OrderRequestDto;
import com.practice.ecommerce.order_service.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
@Slf4j
public class OrderController {

	private final OrderService orderService;
	
	@GetMapping("/helloOrders")
	public String helloOrders() {
		return "Hello from orders Service";
	}
	
	@GetMapping
	public ResponseEntity<List<OrderRequestDto>> getAllOrders(HttpServletRequest httpServletRequest){
		log.info("Feteching all the orders via controller");
		List<OrderRequestDto> orders = orderService.getAllOrdeers();
		return ResponseEntity.ok(orders);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id){
		log.info("feteching orders with id : {} via controller ",id);
		OrderRequestDto order = orderService.getOrderById(id);
		return ResponseEntity.ok(order);
	}
	
	@PostMapping("/create-order")
	public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
			OrderRequestDto orderRequestDto1= orderService.createOrder(orderRequestDto);
			return ResponseEntity.ok(orderRequestDto1);
	}
	
	@DeleteMapping("/cancle-order/{id}")
	public ResponseEntity<OrderRequestDto> cancelOrder(@RequestBody OrderRequestDto cancleOrder,@PathVariable Long id){
		OrderRequestDto orderCanceld = orderService.cancleOrder(cancleOrder,id);
		return ResponseEntity.ok(cancleOrder);
	}
}
