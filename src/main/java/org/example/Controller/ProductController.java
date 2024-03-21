package org.example.Controller;

import org.example.Exception.*;
import org.example.Model.Product;
import org.example.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

        @GetMapping("/product/{id}")
        public ResponseEntity<Product> getProductByIdEndpoint(@PathVariable int id) {
            Product product = null;
            try {
                product = productService.getProductById(id);
                return new ResponseEntity<>(product, HttpStatus.OK);
            } catch (ProductNotFoundException e) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping("/seller/{sellerName}/product")
        public ResponseEntity<Object> postProductEndpoint(@RequestBody Product product, @PathVariable String sellerName) throws SellerNotFoundException {
            try {
                productService.insertProduct(product, sellerName);
                return new ResponseEntity<>(product, HttpStatus.CREATED);
            } catch (SellerNotFoundException e) {
                return new ResponseEntity<>("Seller Not Found", HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                return new ResponseEntity<>("Invalid Product Request", HttpStatus.BAD_REQUEST);
            }
        }

        @DeleteMapping("/product/{id}")
        public ResponseEntity deleteProductByIdEndpoint(@PathVariable int id) {
            try {
                productService.deleteProduct(id);
                return new ResponseEntity<>("Product Deleted!",HttpStatus.OK);
            } catch (ProductNotFoundException e) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }

        @PutMapping("/seller/{sellerName}/product/{id}")
        public ResponseEntity<Object> updateProductEndpoint(@PathVariable int id, @PathVariable String sellerName, @RequestBody Product product){
            try {
                Product newProduct = productService.updateProduct(id, product, sellerName);
                return new ResponseEntity<>(newProduct, HttpStatus.OK);
            } catch (ProductNotFoundException e) {
                return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
            } catch (SellerNotFoundException e) {
                return new ResponseEntity<>("Seller Not Found", HttpStatus.NOT_FOUND);
            } catch (SellerException e) {
                return new ResponseEntity<>("Invalid Seller Request", HttpStatus.NOT_FOUND);
            }
        }
}
