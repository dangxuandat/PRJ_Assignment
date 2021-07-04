/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.cart;

import dat.product.ProductDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartObject implements Serializable{
    private Map<String,Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String itemName){
        //1 check itemName is existed
        if(itemName == null){
            return;
        }
        if(itemName.trim().isEmpty()){
            return;
        }//end if itemName is empty
        
        //2 check existed cart container
        if(this.items == null){
            items = new HashMap<>();
        }//end if cart is existed
        
        //3 checking existed items
        int quantity = 1;
        if(this.items.containsKey(itemName)){
            quantity = this.items.get(itemName) + 1;
        }// end if item is existed
        //4 update cart
        this.items.put(itemName,quantity);
    }
    
    public void removeItemToCart(String itemName){
        //1 check existed cart
        if(this.items == null){
            return;
        }
        //2 checking existed items
       if(this.items.containsKey(itemName)){
               this.items.remove(itemName);
               if(this.items.isEmpty()){
                   items = null;
               }
       }//end if item is existed
    }
}
