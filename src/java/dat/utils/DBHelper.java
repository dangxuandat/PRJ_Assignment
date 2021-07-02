/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
public class DBHelper implements Serializable{
    
    public static Connection makeConnection() throws SQLException, NamingException{
        try {
            //1 get current system filt
            Context context = new InitialContext();
            //2 get container context
            Context tomcatContext = (Context) context.lookup("java:comp/env");
            //3 get DataSource from Tomcat 
            DataSource ds = (DataSource) tomcatContext.lookup("DS");
            // 4 get Connection
            Connection con = ds.getConnection();
            return con;
        }finally{
            
        }
    }
}
