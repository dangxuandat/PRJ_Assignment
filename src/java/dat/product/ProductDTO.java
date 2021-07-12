/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.product;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class ProductDTO implements Serializable {
    private String sku;
    private String name;
    private int price;
    private int quantity;
    private int total;

    public ProductDTO() {
    }

    
    public ProductDTO(String sku,String name, int price, int quantity) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = price*quantity;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "name=" + name + ", price=" + price + ", quantity=" + quantity + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.sku);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductDTO other = (ProductDTO) obj;
        if (!Objects.equals(this.sku, other.sku)) {
            return false;
        }
        return true;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }
    
    

}
