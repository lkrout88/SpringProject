package org.example.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int productId;
    public String productName;
    public double productPrice;

    @ManyToOne
    @JoinColumn(name="product_fk")
    @JsonIgnoreProperties("products")
    public Seller seller;
}


