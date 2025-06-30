package com.practice.ecommerce.order_service.dto;

import java.math.BigInteger;
import java.util.List;

import com.practice.ecommerce.order_service.entity.OrderStauts;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequestDto {

	private Long id;
	private BigInteger totalPrice;
	//private  statusOrderStauts;
	private List<OrderRequstItemDto> items;
	
}
