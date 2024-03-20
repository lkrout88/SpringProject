package org.example.Controller;

import org.example.Exception.SellerNotFoundException;
import org.example.Model.Seller;
import org.example.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @CrossOrigin
    @RestController
    public class SellerController {
        SellerService sellerService;

        @Autowired
        public SellerController(SellerService sellerService) {
            this.sellerService = sellerService;
        }

        @GetMapping("/seller")
        public ResponseEntity<List<Seller>> getAllSellerEndpoint() {
            List<Seller> seller = sellerService.getAllSeller();
            return new ResponseEntity<>(seller, HttpStatus.OK);
        }

        @PostMapping("/seller")
        public ResponseEntity<Object> postSellerEndpoint(@RequestBody Seller seller) throws SellerNotFoundException {
            try {
                sellerService.insertSeller(seller);
                return new ResponseEntity<>(seller, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Invalid Seller Request", HttpStatus.BAD_REQUEST);
            }
        }
    }
