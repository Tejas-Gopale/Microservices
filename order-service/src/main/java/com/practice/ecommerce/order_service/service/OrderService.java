package com.practice.ecommerce.order_service.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.practice.ecommerce.order_service.clients.InventoryOpenFeignClient;
import com.practice.ecommerce.order_service.dto.OrderRequestDto;
import com.practice.ecommerce.order_service.entity.OrderItem;
import com.practice.ecommerce.order_service.entity.OrderStauts;
import com.practice.ecommerce.order_service.entity.Orders;
import com.practice.ecommerce.order_service.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

	private final OrderRepository orderRepository;
	private final ModelMapper modelMapper;
	private final InventoryOpenFeignClient inventoryOpenFeignClient;
	
	public List<OrderRequestDto> getAllOrdeers(){
		log.info("Feteching all orders");
		List<Orders> orders = orderRepository.findAll();
		return orders.stream().map(order -> modelMapper.map(order, OrderRequestDto.class)).toList();
	}
	
	public OrderRequestDto getOrderById(Long id) {
		log.info("Feteching order By Id : {}",id);
		Orders order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order Not found With id" + id));
		return modelMapper.map(order, OrderRequestDto.class);
	}

	@CircuitBreaker(name = "inventoryCircutBeaker" , fallbackMethod = "createOrderFallback" )
	@Retry(name = "inventoryRetry", fallbackMethod = "createOrderFallback")
	@RateLimiter(name ="inventoryRateLimiter", fallbackMethod = "createOrderFallback")
	public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {
		// microservice
		log.info("calling the createOrderRequest...");
		Double totalPrice = inventoryOpenFeignClient.reduceStocks(orderRequestDto);
		
		Orders orders = modelMapper.map(orderRequestDto, Orders.class);
		for(OrderItem orderItem : orders.getItems()) {
			orderItem.setOrder(orders);
		}
		orders.setTotalPrice(totalPrice);
		orders.setOrderStauts(OrderStauts.CONFIRMED);
	    Orders savedOrders=orderRepository.save(orders);
		return modelMapper.map(savedOrders, OrderRequestDto.class);
	}
	
	@Retry(name = "inventoryRetry", fallbackMethod = "createOrderFallback")
	public OrderRequestDto createOrderFallback(OrderRequestDto orderRequestDto,Throwable throwable) {
		log.error("Fallback occured Due to :{} ", throwable.getMessage());
		return new OrderRequestDto();
	}

	public OrderRequestDto cancleOrder(OrderRequestDto cancleOrder,Long id) {
		log.info("cancling the order by id:" );
		//check if order id is valid or not.. for more reliable and execute the below code into if order is valid or simply return order id is not valid
		Double totlaPrice = inventoryOpenFeignClient.increaseStocks(cancleOrder);
		
		Orders cancleOrders = modelMapper.map(cancleOrder, Orders.class);
		
		orderRepository.deleteById(id);
		log.info("order deleted susccessfully");
		return modelMapper.map(cancleOrder, OrderRequestDto.class);
	}
	
} 
