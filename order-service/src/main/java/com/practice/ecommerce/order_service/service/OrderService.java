package com.practice.ecommerce.order_service.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.practice.ecommerce.order_service.dto.OrderRequestDto;
import com.practice.ecommerce.order_service.entity.Orders;
import com.practice.ecommerce.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

	private final OrderRepository orderRepository;
	private final ModelMapper modelMapper;
	
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
	
} 
