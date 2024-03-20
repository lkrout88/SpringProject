package org.example.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class Seller {
    @Id
    public String sellerName;
    @OneToMany
    @JoinColumn(name="seller_fk")
    public List<Product> products;
}
