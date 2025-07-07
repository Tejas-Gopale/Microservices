package com.practice.ecommerce.api_gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoggingOrderFilters extends AbstractGatewayFilterFactory< com.practice.ecommerce.api_gateway.filters.LoggingOrderFilters.Config>{

	public LoggingOrderFilters() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange,chain) -> {
			log.info("Order Filder Pre ... {}, ",exchange.getRequest().getURI());
			return chain.filter(exchange); 
		};	
	}

	public static class Config {	
	}

	
}
