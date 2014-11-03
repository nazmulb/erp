package com.erp.model.user;

import com.erp.dal.MysqlConnection;
import com.erp.entity.user.TblUser;
import java.sql.*;
import java.util.ArrayList;

/**
 * <h1>ERP Model</h1>
 * User Model Class which is related to below the table:
 * tbl_user
 * 
 * @author  nazmul.basher@gmail.com
 * @version 1.0
 * @since   2009-04-21 
 */
public class UserModel 
{
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private int noOfRecords = 0;
    private int lastInsertedId = -1;
        
    public UserModel()
    {
        MysqlConnection mysqlCon = MysqlConnection.getInstance();
        conn = mysqlCon.getConnection();
    }
    
    /**
     * To get the no of record found.
     * @return int no of record found.
     */
    public int getNoOfRecords() 
    {
        return noOfRecords;
    }
    
    /**
     * To get the last inserted id.
     * @return int no of record found.
     */
    public int getLastInsertedId() 
    {
        return lastInsertedId;
    }
    
    /**
     * To get all users.
     * @return ArrayList<TblUser> This will return list of users.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblUser> load() throws SQLException 
    {
        return load(-1, -1);
    }
    
    
    /**
     * To get users with limit.
     * @param offset
     * @param noOfRecords
     * @return ArrayList<TblUser> This will return list of users.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblUser> load(int offset, int noOfRecords) throws SQLException 
    {
        ResultSet rs = null;
        ArrayList<TblUser> userList = new ArrayList<TblUser>();
        
        try {    
           String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM tbl_user "+((offset==-1 && noOfRecords==-1) ? "" :  " LIMIT ?, ?");
           
           pstmt = conn.prepareStatement(sql);
           if(!(offset==-1 && noOfRecords==-1)){
                pstmt.setInt(1, offset);
                pstmt.setInt(2, noOfRecords);
           }
           
           rs = pstmt.executeQuery();

           while(rs.next()){
               TblUser u = new TblUser();
               u.setUid(rs.getInt("uid"));
               u.setUname(rs.getString("uname"));
               u.setFirstName(rs.getString("first_name"));
               u.setLastName(rs.getString("last_name"));
               u.setPassword(rs.getString("password"));
               u.setEmail(rs.getString("email"));
               u.setPhone(rs.getString("phone"));
               u.setImage(rs.getString("image"));
               u.setStatus(rs.getInt("status"));
               
               userList.add(u);
           } 
           
           pstmt = conn.prepareStatement("SELECT FOUND_ROWS()");
           rs = pstmt.executeQuery();
           if(rs.next())
                this.noOfRecords = rs.getInt(1);
         
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
            rs.close();
            pstmt.close();
            conn.close();
        }
        
        return userList;   
    }
    
    /**
     * Get user by user id.
     * @param uid
     * @return TblUser This will return user info.
     * @exception SQLException On SQL error.
     */
    public TblUser loadById(int uid) throws SQLException 
    {
        ResultSet rs = null;
        TblUser u = new TblUser();
        
        try {    
           String sql = "SELECT * FROM tbl_user WHERE uid=? LIMIT 1";
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, uid);
           
           rs = pstmt.executeQuery();

           if(rs.next()){
               u.setUid(rs.getInt("uid"));
               u.setUname(rs.getString("uname"));
               u.setFirstName(rs.getString("first_name"));
               u.setLastName(rs.getString("last_name"));
               u.setPassword(rs.getString("password"));
               u.setEmail(rs.getString("email"));
               u.setPhone(rs.getString("phone"));
               u.setImage(rs.getString("image"));
               u.setStatus(rs.getInt("status"));
           }
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
        }
        
        return u;
    }
        
    /**
     * Get user by user name.
     * @param uname
     * @return TblUser This will return user info.
     * @exception SQLException On SQL error.
     */
    public TblUser loadByUserName(String uname) throws SQLException 
    {
        ResultSet rs = null;
        TblUser u = new TblUser();
        
        try {    
           String sql = "SELECT * FROM tbl_user WHERE uname=? LIMIT 1";
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, uname);
           
           rs = pstmt.executeQuery();

           if(rs.next()){
               u.setUid(rs.getInt("uid"));
               u.setUname(rs.getString("uname"));
               u.setFirstName(rs.getString("first_name"));
               u.setLastName(rs.getString("last_name"));
               u.setPassword(rs.getString("password"));
               u.setEmail(rs.getString("email"));
               u.setPhone(rs.getString("phone"));
               u.setImage(rs.getString("image"));
               u.setStatus(rs.getInt("status"));
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
        
        return u;   
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
        ResultSet rs = null;
        boolean isValid = false;
        
        try {    
           String sql = "SELECT COUNT(1) AS counts FROM tbl_user WHERE status=1 AND uname=? AND password=?";
           
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
    
    /**
     * To check a user is exist or not.
     * @param uname User Name.
     * @return boolean This will return is the user exist or not.
     * @exception SQLException On SQL error.
    */
    public boolean isExist(String uname) throws SQLException 
    {
        ResultSet rs = null;
        boolean isExist = false;
        
        try {    
           String sql = "SELECT COUNT(1) AS counts FROM tbl_user WHERE uname=?";
           
           pstmt = conn.prepareStatement(sql); 
           pstmt.setString(1, uname);
           rs = pstmt.executeQuery();

           if(rs.next()){
              isExist = (rs.getInt("counts")>0 ? true : false);
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
        
        return isExist;   
    }
    
    /**
     * Add or update user depending on user id.
     * @param user object.
     * @exception SQLException On SQL error.
     */
    public void save(TblUser user) throws SQLException 
    {
        try {
            int uid = user.getUid();
            String sql;
            
            if(uid>0){ 
                sql = "UPDATE tbl_user SET uname=?, first_name=?, last_name=?, password=?, email=?, phone=?  WHERE uid=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(7, uid);
            }else{
                sql = "INSERT INTO tbl_user (uname, first_name, last_name, password, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            
            pstmt.setString(1, user.getUname());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getPhone());
            pstmt.executeUpdate();
            
            if(uid==0){ 
                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next()){
                    this.lastInsertedId = rs.getInt(1);
                }
            }
         
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
            pstmt.close();
            conn.close();
        }
    }
    
    /**
     * Update status by user id.
     * @param uid User ID.
     * @param status User Status.
     * @exception SQLException On SQL error.
    */
    public void updateStatus(int uid, int status) throws SQLException 
    {
        try {    
           String sql = "UPDATE tbl_user SET status=? WHERE uid=?";
           
           pstmt = conn.prepareStatement(sql); 
           pstmt.setInt(1, status);
           pstmt.setInt(2, uid);
           pstmt.executeUpdate();      
         
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
            pstmt.close();
            conn.close();
        }
    }
}
