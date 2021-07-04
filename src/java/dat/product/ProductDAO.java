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
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class ProductDAO implements Serializable{
    private ArrayList<ProductDTO> listProduct;

    public ArrayList<ProductDTO> getListProduct() {
        return listProduct;
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
                String sql = "Select NAME, PRICE, QUANTITY "
                            +"From Product";
                            
                stm = con.prepareStatement(sql);
                result = stm.executeQuery();
                while(result.next()){
                    String name = result.getString("NAME");
                    int price = result.getInt("PRICE");
                    int quantity = result.getInt("QUANTITY");
                    ProductDTO item = new ProductDTO(name, price, quantity);
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
}
