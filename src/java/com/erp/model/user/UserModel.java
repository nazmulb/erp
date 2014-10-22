package com.erp.model.user;

import com.erp.dal.MysqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h1>ERP Model</h1>
 * User Model Class which is related to below the table:
 * tbl_user
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-21 
 */
public class UserModel 
{
    private static Connection conn = null;
    
    public UserModel()
    {
        MysqlConnection mysqlCon = MysqlConnection.getInstance();
        conn = mysqlCon.getConnection();
    }
    
    /**
     * To check a user is valid or not.
     * @param uname User Name.
     * @param password User Password.
     * @return boolean This will return is the user valid or not.
     * @exception SQLException On SQL error.
    */
    public boolean isValidUser(String uname, String password) throws SQLException 
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isValid = false;
        
        try {    
           String sql = "SELECT COUNT(1) AS counts FROM  tbl_user WHERE status=1 AND uname=? AND password=?";
           
           pstmt = conn.prepareStatement(sql); 
           pstmt.setString(1, uname);
           pstmt.setInt(2, password.hashCode());
           rs = pstmt.executeQuery();

           if(rs.next()){
              isValid = (rs.getInt("counts")>0 ? true : false);
           }        
         
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
            rs.close();
            pstmt.close();
            conn.close();
        }
        
        return isValid;   
    }
}
