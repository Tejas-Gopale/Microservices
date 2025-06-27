package com.practice.ecommerce.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.ecommerce.inventory_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
