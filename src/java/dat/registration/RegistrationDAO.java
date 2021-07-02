/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.registration;

import dat.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class RegistrationDAO implements Serializable{
    private List<RegistrationDTO> list_searched_account;
    public RegistrationDTO checkLogin(String username, String password) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet result = null;
        RegistrationDTO login_acc = null;
        try {
            // 1 get connection
            con = DBHelper.makeConnection();
            if(con != null){
                //2 create string sql
                String sql = "Select fullname, isAdmin "
                            +"From Registration "
                            +"Where username = ? and password = ?";
                //3 create query obj
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4 execute query
                result = stm.executeQuery();
                //5 process result
                if(result.next()){
                    String fullname = result.getString("fullname");
                    boolean isAdmin = result.getBoolean("isAdmin");
                    login_acc = new RegistrationDTO(username, password, fullname, isAdmin);
                    return login_acc;
                }//end result loop
            }//end if connect successfully
        } catch (SQLException | NamingException e) {
            
        }finally{
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
    
    public List<RegistrationDTO> SearchByFullName(String FullName) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet result = null;
        try {
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "Select username, password, fullname, isAdmin "
                            +"From Registration "
                            +"Where fullname LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, FullName);
                result = stm.executeQuery();
                while(result.next()){
                    String username = result.getString("username");
                    String password = result.getString("password");
                    String fullname = result.getString("fullname");
                    boolean isAdmin = result.getBoolean("isAdmin");
                    RegistrationDTO searched_account = new RegistrationDTO(username, password, fullname, isAdmin);
                    if(this.list_searched_account == null){
                        list_searched_account = new ArrayList<>();
                    }// end if list search account is null
                    this.list_searched_account.add(searched_account);
                }//end result lopp
                return list_searched_account;
            }//end if connect succesfully
        } catch (SQLException | NamingException e) {
            
        }finally{
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
