package com.mcfadyen.challenge.repository;

import com.mcfadyen.challenge.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}