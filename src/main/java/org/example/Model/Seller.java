package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
    public class Seller {
    @Id
    public String sellerName;
    @OneToMany
    @JoinColumn(name="seller_fk")
    public List<Product> Products;

}
