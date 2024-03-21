package org.example.Service;

import org.example.Exception.SellerException;
import org.example.Exception.SellerNotFoundException;
import org.example.Main;
import org.example.Model.*;
import org.example.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class SellerService {
        SellerRepository sellerRepository;

        @Autowired
        public SellerService(SellerRepository sellerRepository) {
            this.sellerRepository = sellerRepository;
        }

        public List<Seller> getAllSeller(){
            Main.log.info("GET: Attempting to get all Sellers.");
            return sellerRepository.findAll();
        }

        public Seller insertSeller (Seller seller) throws SellerNotFoundException, SellerException {
            Main.log.info("ADD: Attempting to add a Seller: " + seller.sellerName);
            List<Seller> existingSeller = sellerRepository.findBySellerName(seller.getSellerName());
            if (!existingSeller.isEmpty()) {
                Main.log.warn("ADD: Seller already exists.");
                throw new SellerNotFoundException("Seller already exists.");
            }
            else if (seller.getSellerName() == null || seller.getSellerName().isEmpty()) {
                Main.log.warn("ADD: Incorrect Seller input from user.");
                throw new SellerException("Seller Name cannot be blank.");
            }
            return sellerRepository.save(seller);
        }
    }


