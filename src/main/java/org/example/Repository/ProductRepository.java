package org.example.Repository;

import org.example.Model.Product;

import org.example.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
