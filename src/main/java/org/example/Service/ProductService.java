package org.example.Service;
import org.example.Exception.*;
import org.example.Main;
import org.example.Model.*;
import org.example.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {
    SellerRepository sellerRepository;
    ProductRepository productRepository;
    @Autowired
    public ProductService(SellerRepository sellerRepository, ProductRepository productRepository){
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        Main.log.info("GET: Attempting to get all Product.");
        return productRepository.findAll();
    }

    //Below method will return true- if the seller is already in the database-
    public Seller checkSellerNameExists(Product product, String sellerName) throws SellerException {
        Main.log.info("CHECK: Attempting to check if a Seller exists.");
        if (product.productName == null || product.productName.isEmpty() || sellerName == null || sellerName.isEmpty() || product.productPrice <= 0) {
            Main.log.warn("CHECK: Product Name and Seller Name cannot be blank and Product Price must be > 0.");
            throw new SellerException("Product Name and Seller Name cannot be blank and Product Price must be > 0.");
        }
        List<Seller> sellerList = sellerRepository.findBySellerName(sellerName);
        if (sellerList.isEmpty()) {
            return null;
        }
        else {
            return sellerList.get(0);
        }
    }

    //this method will check the value (true or false returned from the checkSellerNameExists method before adding the product
    public Product insertProduct(Product p, String sellerName) throws SellerNotFoundException, SellerException {
        Main.log.info("ADD: Attempting to add a Product: " + p.getProductName());
        Seller sellerExists = checkSellerNameExists(p, sellerName);
        if (sellerExists != null) {
            p.setSeller(sellerExists);
            productRepository.save(p);
            sellerExists.getProducts().add(p);
            sellerRepository.save(sellerExists);
        } else {
            Main.log.warn("ADD: Seller does not exist.");
            throw new SellerNotFoundException("SellerName must exist in Seller database.");
        }
        return p;
    }

    //method below returns the product details when a product id is entered by the client
    public Product getProductById(int id) throws ProductNotFoundException{
        Main.log.info("GET: Attempting to get a Product by ID.");
        Optional<Product> p = productRepository.findById(Integer.toString(id));
        if (p.isEmpty()) {
            Main.log.warn("GET: Product not found.");
            throw new ProductNotFoundException("Product Not Found!");
        }
        else{
            return p.get();
        }
    }

    public void deleteProduct(int productId) throws ProductNotFoundException {
        Main.log.info("DELETE: Attempting to delete a Product by ID.");
        Product productToDelete = null;
        try {
            productToDelete = getProductById(productId);
            productRepository.delete(productToDelete);
        } catch (ProductNotFoundException e) {
            Main.log.warn("DELETE: Product not found.");
            throw new ProductNotFoundException("Product Not Found!");
        }
    }

    //Method will update the product values when the client does a put.  This method will call other methods
    //to check if
    public Product updateProduct(int id, Product updatedProduct, String sellerName) throws ProductNotFoundException, SellerNotFoundException, SellerException {
        Main.log.info("UPDATE: Attempting to update a Product by ID.");
        Product productToUpdate = null;
        Seller newSeller = checkSellerNameExists(updatedProduct, sellerName);
        try {
            productToUpdate = getProductById(id);
            if (newSeller == null) {
                Main.log.warn("UPDATE: Seller not found.");
                throw new SellerNotFoundException("SellerName must exist in Seller database");
            } else {
                Seller oldSeller = productToUpdate.getSeller();
                oldSeller.getProducts().remove(productToUpdate);
                sellerRepository.save(oldSeller);

                productToUpdate.getSeller().getProducts().remove(productToUpdate);
                productToUpdate.setProductName(updatedProduct.getProductName());
                productToUpdate.setProductPrice(updatedProduct.getProductPrice());
                productToUpdate.setSeller(newSeller);
                productRepository.save(productToUpdate);
                newSeller.getProducts().add(productToUpdate);
                sellerRepository.save(newSeller);
            }
        } catch (ProductNotFoundException e) {
            Main.log.warn("UPDATE: Product not found.");
            throw new ProductNotFoundException("Product Not Found!");
        }
        return null;
    }
}
