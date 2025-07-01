package com.practice.ecommerce.inventory_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OrderRequestDto {

	private List<OrderRequestItemDto> items;
}
