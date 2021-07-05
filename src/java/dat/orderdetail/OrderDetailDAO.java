/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.orderdetail;

import dat.product.ProductDTO;
import dat.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class OrderDetailDAO implements Serializable{
    public boolean insertBillIntoDB(List<ProductDTO> checkoutItems) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        int effectRow = 0;
        try {
            // 1 get Connection
            con = DBHelper.makeConnection();
            if(con != null){
                for (ProductDTO itemDTO : checkoutItems) {
                    //2 create String sql
                    String sql = "INSERT INTO OrderDetail (NAME, QUANTITY, PRICE, TOTAL) "
                            +"VALUES (?,?,?,?)";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, itemDTO.getName());
                    stm.setInt(2, itemDTO.getQuantity());
                    stm.setInt(3, itemDTO.getPrice());
                    stm.setInt(4, itemDTO.getTotal());
                    effectRow = stm.executeUpdate();
                }
                if(effectRow > 0){
                    return true;
                }
            }
        }finally{
            
            if(stm != null){
                stm.close();
            }
            
            if(con != null){
                con.close();
            }
        }
        return false;
    }
}
