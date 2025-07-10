package com.practice.ecommerce.api_gateway.service;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {
 
	@Value("${jwt.secretKey}")
	private String jwtSecretKey;
	
	private SecretKey getSecureKey(){
		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public Long getUserIdFromToken(String token) {
		Claims claim = Jwts.parser()
				.verifyWith(getSecureKey())
				.build()
				.parseSignedClaims(token).getPayload();
		
		return Long.valueOf(claim.getSubject());
	}
	
	public String getUserRoleFromToken(String token) {
		Claims claims= Jwts.parser().verifyWith(getSecureKey()).build()
				.parseSignedClaims(token).getPayload();
		
		return claims.get("role",String.class);
		
	}
}
