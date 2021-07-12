/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.product;

import dat.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class ProductDAO implements Serializable{
    private ArrayList<ProductDTO> listProduct;
    
    private List<ProductDTO> listItemInViewCart;

    public ArrayList<ProductDTO> getListProduct() {
        return listProduct;
    }

    public List<ProductDTO> getListItemInViewCart() {
        return listItemInViewCart;
    }
    
    public void loadsListProductFromDatabase() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet result = null;
        
        try {
            //1 get connect
            con = DBHelper.makeConnection();
            if(con != null){
                //2 create String sql
                String sql = "Select SKU, NAME, PRICE, QUANTITY "
                            +"From Product";
                            
                stm = con.prepareStatement(sql);
                result = stm.executeQuery();
                while(result.next()){
                    String sku = result.getString("SKU");
                    String name = result.getString("NAME");
                    int price = result.getInt("PRICE");
                    int quantity = result.getInt("QUANTITY");
                    ProductDTO item = new ProductDTO(sku,name, price, quantity);
                    if(this.listProduct == null){
                        listProduct = new ArrayList<>();
                    }//end if listProduct is empty
                    listProduct.add(item);
                }//end result loop
            }// end if connection is successful
        } finally {
            if(result != null){
                result.close();
            }
            
            if(stm != null){
                stm.close();
            }
            
            if(con != null){
                con.close();
            }
        }
    }
    public ArrayList<ProductDTO> loadTotalPriceOfItemToViewCart(Map<String,Integer> itemCart) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet result = null;
        try {
            //1 get Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                for (String skuItem : itemCart.keySet()) {
                    //2 Create String sql
                    String sql = "Select NAME, PRICE "
                            +"From Product "
                            +"Where SKU = ?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, skuItem);
                    result = stm.executeQuery();
                    int quantity = itemCart.get(skuItem);
                    if(listItemInViewCart == null){
                        listItemInViewCart = new ArrayList<>();
                    }//end if list Item In View Cart is null
                    while (result.next()) {
                        String name = result.getString("NAME");
                        int price = result.getInt("PRICE");
                        ProductDTO dto = new ProductDTO(skuItem,name,price,quantity);
                        listItemInViewCart.add(dto);
                    }//end if result ends
                }// loop keyset in ItemCart
            }//end if Connection is successful
        } finally {
            
            if(result != null){
                result.close();
            }
            
            if(stm != null){
                stm.close();
            }
            
            if(con != null){
                con.close();
            }
        }
        return null;
    }
}
