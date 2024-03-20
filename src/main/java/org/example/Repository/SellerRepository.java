package org.example.Repository;

import org.example.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface SellerRepository extends JpaRepository<Seller, String>{
    List<Seller> findBySellerName(String sellerName);
}
