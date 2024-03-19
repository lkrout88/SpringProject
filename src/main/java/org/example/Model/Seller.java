package org.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;



@Entity
    public class Seller {

@Id
        public String sellerName;

        public Seller(){

        }

        public Seller(String sellerName){
            this.sellerName= sellerName;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(sellerName);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            org.example.Model.Seller seller = (org.example.Model.Seller) o;
            return Objects.equals(sellerName, seller.sellerName);

        }

        @Override
        public String toString() {
            return "Seller{" +
                    "sellerName='" + sellerName + '\'' +
                    '}';
        }
}
