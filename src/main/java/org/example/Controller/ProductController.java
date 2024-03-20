package org.example.Controller;

import org.example.Exception.SellerNotFoundException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @CrossOrigin
    @RestController
    public class ProductController {
        ProductService productService;

        @Autowired
        public ProductController(ProductService productService) {
            this.productService = productService;
        }

        @GetMapping("/product")
        public ResponseEntity<List<Product>> getAllProductEndpoint() {
            List<Product> product = productService.getAllProducts();
            return new ResponseEntity<>(product, HttpStatus.OK);
        }

        @PostMapping("/seller/{sellerName}/product")
        public ResponseEntity<Object> postProductEndpoint(@RequestBody Product product, @PathVariable String sellerName) throws SellerNotFoundException {
            productService.insertProduct(product, sellerName);
            try {
                return new ResponseEntity<>(product, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Invalid Product request", HttpStatus.BAD_REQUEST);
            }
        }

        @DeleteMapping("/product/{id}")
        public ResponseEntity deleteProductEndpoint(@RequestBody int productId){
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.OK);

        }
/*
        @PostMapping("product/{id}")
        public ResponseEntity updateProductEndpoint(@RequestBody int id, Product updatedProduct){
            productService.updateProduct/(updatedProduct);
            return new ResponseEntity<>(HttpStatus.OK);
      }
      */

}
