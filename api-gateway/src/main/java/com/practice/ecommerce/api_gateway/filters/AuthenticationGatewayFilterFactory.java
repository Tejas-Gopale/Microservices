package com.practice.ecommerce.api_gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.practice.ecommerce.api_gateway.service.JwtService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<com.practice.ecommerce.api_gateway.filters.AuthenticationGatewayFilterFactory.Config>{
	
	private final JwtService jwtService;
	
	public AuthenticationGatewayFilterFactory(JwtService  jwtService) {
		super(Config.class);
		this.jwtService = jwtService;
	}

	//inner 
	@Data
	public static class Config {
		private boolean enabled;
	}
	
//implemented method
	@Override
	public GatewayFilter apply(Config config) {
		return (exchange , chain) ->{
			
			if(!config.enabled) return chain.filter(exchange);
			
			String authorizationHeaders = exchange.getRequest().getHeaders().getFirst("Authorization");
			
			if(authorizationHeaders == null) {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}
			
			String token = authorizationHeaders.split("Bearer ")[1];
			
			Long userId = jwtService.getUserIdFromToken(token);
			
			exchange.getRequest()
				.mutate()
				.header("X-User-Id", userId.toString()).build();
			
			return chain.filter(exchange);
		};
	}

	
}
