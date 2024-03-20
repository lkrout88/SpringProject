package org.example.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
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
    @JsonIgnore
    @ManyToOne
    @JsonIgnoreProperties("Products")
    public Seller seller;

}


