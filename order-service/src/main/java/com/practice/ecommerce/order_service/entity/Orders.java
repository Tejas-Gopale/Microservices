package com.practice.ecommerce.order_service.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	@Enumerated(EnumType.STRING)
	private OrderStauts orderStauts;
	
	private Double totalPrice;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL , orphanRemoval = true)
	private List<OrderItem> items;

}
