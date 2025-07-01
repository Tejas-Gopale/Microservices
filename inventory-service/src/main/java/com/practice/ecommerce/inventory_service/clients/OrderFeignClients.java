package com.practice.ecommerce.inventory_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
// name == appication name of other microservice
import org.springframework.web.bind.annotation.GetMapping;
@FeignClient(name ="order-service" , path = "/orders")
public interface OrderFeignClients {

	@GetMapping("/core/helloOrders")
	public String helloOrders();
}
