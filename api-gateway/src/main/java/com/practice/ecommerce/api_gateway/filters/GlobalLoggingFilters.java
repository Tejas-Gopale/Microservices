package com.practice.ecommerce.api_gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalLoggingFilters implements GlobalFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		//Per-filter
		log.info("Loggin From Global preee ....................", exchange.getRequest().getURI());
		return chain.filter(exchange).then(Mono.fromRunnable(()-> {
			log.info("Loging from global POST  ........ ------> ",exchange.getResponse().getStatusCode());
		}));
	
	}

}
