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

        //old code before adding the SellerDAO
        // public SellerService(){
        // sellerList = new ArrayList<>();
        // }

        public List<Seller> getAllSeller(){
            return sellerRepository.findAll();
        }
        // old code before added the sellerDAO
        // public  List<Seller> getSellerList(){
        //   return sellerList;
        // }

        public Seller insertSeller (Seller seller) {
            //List<Seller> sellerList;
            //Main.log.info("ADD:  Attempting to add a Seller:" + seller.sellerName);
            List<Seller> existingSeller = getAllSeller();
            for (int i = 0; i < existingSeller.size(); i++) {
                // seller = sellerList.get(i);
                if (seller.sellerName.equals(existingSeller.get(i).getSellerName())) {
                    //System.out.println(""+ seller + sellerList.get(i));
                   // Main.log.warn("ADD:  Seller name already exists: " + seller.sellerName);
                    //throw new ProductException("Seller Name already exists");
                }

            }
            // do I need this line?  isn't the DAO adding it?
            //sellerList.add(seller);
            this.sellerRepository.save(seller);
            return seller;
        }
    }


