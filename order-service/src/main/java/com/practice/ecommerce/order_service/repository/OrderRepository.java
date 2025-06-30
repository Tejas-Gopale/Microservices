package com.practice.ecommerce.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.ecommerce.order_service.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

}
