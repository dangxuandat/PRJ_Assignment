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
    
    public void addItemToCart(String itemSku){
        //1 check itemSku is existed
        if(itemSku == null){
            return;
        }
        if(itemSku.trim().isEmpty()){
            return;
        }//end if itemSku is empty
        
        //2 check existed cart container
        if(this.items == null){
            items = new HashMap<>();
        }//end if cart is existed
        
        //3 checking existed items
        int quantity = 1;
        if(this.items.containsKey(itemSku)){
            quantity = this.items.get(itemSku) + 1;
        }// end if item is existed
        //4 update cart
        this.items.put(itemSku,quantity);
    }
    
    public void removeItemToCart(String itemSku){
        //1 check existed cart
        if(this.items == null){
            return;
        }
        //2 checking existed items
       if(this.items.containsKey(itemSku)){
               this.items.remove(itemSku);
               if(this.items.isEmpty()){
                   items = null;
               }
       }//end if item is existed
    }
    public int getItemQuanityBySku(String itemSku){
        //check existed sku
        if(itemSku == null || itemSku.trim().isEmpty()){
            return 0;
        }
        //chech existed this.items
        if(this.items == null){
            return 0;
        }
        
        if(this.items.containsKey(itemSku)){
            return this.items.get(itemSku);
        }
        return 0;
    }
}
