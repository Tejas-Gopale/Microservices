package com.practice.ecommerce.order_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequstItemDto {

	private Long id;
	private Long productId;
	private Integer quanity;
}
