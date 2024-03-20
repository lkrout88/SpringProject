package org.example.Service;

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
            return sellerRepository.findAll();
        }

        public Seller insertSeller (Seller seller) throws SellerNotFoundException {
            //Main.log.info("ADD:  Attempting to add a Seller:" + seller.sellerName);
            List<Seller> existingSeller = sellerRepository.findBySellerName(seller.getSellerName());
            if (!existingSeller.isEmpty()) {
                throw new SellerNotFoundException("Seller already exists");
            }
            return sellerRepository.save(seller);
        }
    }


