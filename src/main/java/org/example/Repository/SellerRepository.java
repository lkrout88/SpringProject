package org.example.Repository;

import org.example.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, String>{
}
