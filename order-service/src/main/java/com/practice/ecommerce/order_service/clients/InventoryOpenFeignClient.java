package com.practice.ecommerce.order_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.practice.ecommerce.order_service.dto.OrderRequestDto;

@FeignClient(name = "inventory-service" , path = "/inventory")
public interface InventoryOpenFeignClient {

	@PutMapping("/products/reduce-stocks")
	Double reduceStocks(@RequestBody OrderRequestDto orderRequestDto);

	@PutMapping("/products/increase-stock")
	Double increaseStocks(OrderRequestDto cancleOrder);
}
