package org.example.Service;

import org.example.Main;
import org.example.Model.Seller;
import org.example.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;





@Service
    public class SellerService {

        SellerRepository sellerRepository;
        @Autowired
        public SellerService(SellerRepository sellerRepository){

            this.sellerRepository = sellerRepository;
        }
        List<Seller> sellerList;



        public List<Seller> getAllSeller(){
            return sellerRepository.findAll();
        }


        public Seller insertSeller (Seller seller) {
            //List<Seller> sellerList;
            //Main.log.info("ADD:  Attempting to add a Seller:" + seller.sellerName);
            List<Seller> existingSeller = getAllSeller();
            for (int i = 0; i < existingSeller.size(); i++) {
                // seller = sellerList.get(i);
                if (seller.sellerName.equals(existingSeller.get(i).sellerName)) {
                    // Main.log.warn("ADD:  Seller name already exists: " + seller.sellerName);

                }

            }

            this.sellerRepository.save(seller);
            return seller;
        }
    }


