package com.practice.ecommerce.inventory_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequestItemDto {

	private Long productId;
	private Integer quanity;
}
