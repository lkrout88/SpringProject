package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;



@Entity

@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
    public class Seller {
    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public List<Product> getProducts() {
        return Products;
    }

    public void setProducts(List<Product> products) {
        Products = products;
    }

    @Id
public String sellerName;
@OneToMany
@JoinColumn(name="sellerName_fk")
    public List<Product> Products;

}
