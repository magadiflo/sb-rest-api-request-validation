package com.magadiflo.app.repository;

import com.magadiflo.app.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
