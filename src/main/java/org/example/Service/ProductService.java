package org.example.Service;
import org.example.Exception.SellerNotFoundException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Repository.ProductRepository;
import org.example.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    SellerRepository sellerRepository;
    ProductRepository productRepository;
    @Autowired
    public ProductService(SellerRepository sellerRepository, ProductRepository productRepository){
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }
    SellerService sellerService;
    ProductService productService;
   // List<Product> productList;


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }




    //Below method will return true- if the seller is already in the database-
    public Seller checkSellerNameExists(Product product, String sellerName) throws SellerNotFoundException {
        Seller seller = null;
        if (product.productName == null || product.productName.isEmpty() || sellerName == null || sellerName.isEmpty() || product.productPrice <= 0) {
            throw new SellerNotFoundException("Product Name and Seller Name cannot be blank an Product Price must be > 0");
        }
        // sellerService = new SellerService();
        //List<Seller> sellerList = sellerService.getAllSeller();
        List<Seller> existingSeller = sellerRepository.findAll();
        //System.out.println("seller list" + sellerList.size());
        for (int i = 0; i < existingSeller.size(); i++) {
            if (sellerName.equals(existingSeller.get(i).sellerName)) {
                /*long id = (long) (Math.random() * Long.MAX_VALUE);
                p.setProductId(id);
                productList.add(p);
             */
                seller = existingSeller.get(i);
            }
        }
        return seller;
    }

    //this method will check the value (true or false returned from the checkSellerNameExists method before adding the product
    public Product insertProduct(Product p, String sellerName) throws SellerNotFoundException {
        //Main.log.info("ADD: Attempting to add a Product:");
        Seller sellerExists = checkSellerNameExists(p, sellerName);
        //System.out.println(sellerExists);
        // 201 - resource created
        // List<Product> productList = new ArrayList<>();
        if (sellerExists != null) {
            p.setSeller(sellerExists);
            productRepository.save(p);
            sellerExists.getProducts().add(p);
            sellerRepository.save(sellerExists);
            //if productService returns false then do the rest
        } else {
            //Main.log.warn("ADD: Seller does not exist" + p.sellerName);
            throw new SellerNotFoundException("SellerName must exist in Seller database");
        }
        return p;

    }

    //method below returns the product details when a product id is entered by the client
    public Product getProductById(int id) {
        // long ids= Long.parseLong(String.valueOf((id)));
        List<Product> existingProducts = productService.getAllProducts();
        for (int i = 0; i < existingProducts.size(); i++) {
            Product currentProduct = existingProducts.get(i);
            if (currentProduct.getProductId() == id) {

                //System.out.println("current product" + currentProduct);
                return currentProduct;

            }
        }
        return null;
    }


    public Product deleteProduct(int productId) {

        Product productToDelete = getProductById(productId);

        if (productToDelete != null) {
            productService.deleteProduct(productToDelete.getProductId());
        }
        return productToDelete;

    }



    //Method will update the product values when the client does a put.  This method will call other methods
    //to check if

    /*
    public Product updateProduct(int id, Product updatedProduct) {
        boolean sellerExists;

        Product productToUpdate = getProductById(id);
        System.out.println(productToUpdate.productId);

        if (productToUpdate != null) {
            try {
                if (checkSellerNameExists(updatedProduct)) {
                    productToUpdate.setProductName(updatedProduct.getProductName());
                    productToUpdate.setProductPrice(updatedProduct.getProductPrice());
                    productToUpdate.setSellerName(updatedProduct.getSellerName());
                    productToUpdate.setProductId(id);

                    //keep product ID the same
                    //productToUpdate.setProductId(id);

                    productService.updateProduct(productToUpdate);

                } else {

                    return null;
                }
            } catch (Exception e) {
                return null;
            }
            return productToUpdate;
        }
        return null;

    }
*/
}
