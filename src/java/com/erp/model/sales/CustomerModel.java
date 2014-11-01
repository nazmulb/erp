package com.erp.model.sales;

import com.erp.dal.MysqlConnection;
import com.erp.entity.sales.TblCustomer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * <h1>ERP Model</h1>
 * Customer Model Class which is related to below the table:
 * tbl_customer
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2009-05-22
 */
public class CustomerModel 
{
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private int noOfRecords = 0;
    private int lastInsertedId = -1;
    
    public CustomerModel()
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
     * Get all customers.
     * @param status
     * @return ArrayList<TblCustomer> This will return list of customers.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblCustomer> load(int status) throws SQLException 
    {
        return load(-1, -1, status);
    }
    
    
    /**
     * Get customers with limit.
     * @param offset
     * @param noOfRecords
     * @param status
     * @return ArrayList<TblCustomer> This will return list of customers.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblCustomer> load(int offset, int noOfRecords, int status) throws SQLException 
    {
        ResultSet rs = null;
        ArrayList<TblCustomer> cList = new ArrayList<TblCustomer>();
        
        try {    
           String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM tbl_customer WHERE "+(status==-1 ? "?" : "status = ? ")+((offset==-1 && noOfRecords==-1) ? "" :  " LIMIT ?, ?");
           
           pstmt = conn.prepareStatement(sql);
           
           if(status==-1){
               pstmt.setInt(1, 1);
           }else{
               pstmt.setInt(1, status);
           }
           
           if(!(offset==-1 && noOfRecords==-1)){
                pstmt.setInt(2, offset);
                pstmt.setInt(3, noOfRecords);
           }
           
           rs = pstmt.executeQuery();

           while(rs.next()){
               TblCustomer c = new TblCustomer();
               c.setCid(rs.getInt("cid"));
               c.setName(rs.getString("name"));
               c.setAddress(rs.getString("address"));
               c.setEmail(rs.getString("email"));
               c.setPhone(rs.getString("phone"));
               c.setStatus(rs.getInt("status"));
               
               cList.add(c);
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
        
        return cList;   
    }
    
    /**
     * Get customer by customer id.
     * @param cid
     * @return TblCustomer This will return customer details.
     * @exception SQLException On SQL error.
     */
    public TblCustomer loadById(int cid) throws SQLException 
    {
        ResultSet rs = null;
        TblCustomer c = new TblCustomer();
        
        try {    
           String sql = "SELECT * FROM tbl_customer WHERE cid=?";
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, cid);
           
           rs = pstmt.executeQuery();

           if(rs.next()){
               c.setCid(rs.getInt("cid"));
               c.setName(rs.getString("name"));
               c.setAddress(rs.getString("address"));
               c.setEmail(rs.getString("email"));
               c.setPhone(rs.getString("phone"));
               c.setStatus(rs.getInt("status"));
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
        
        return c;   
    }
    
    /**
     * Add or update customer depending on customer id.
     * @param customer object.
     * @exception SQLException On SQL error.
     */
    public void save(TblCustomer customer) throws SQLException 
    {
        try {
            int cid = customer.getCid();
            String sql;
            
            if(cid>0){ 
                sql = "UPDATE tbl_customer SET name=?, address=?, email=?, phone=? WHERE cid=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(5, cid);
            }else{
                sql = "INSERT INTO tbl_customer (name, address, email, phone) VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getAddress());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhone());
            pstmt.executeUpdate();
            
            if(cid==0){ 
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
     * Update status by customer id.
     * @param cid Customer ID.
     * @param status Customer Status.
     * @exception SQLException On SQL error.
    */
    public void updateStatus(int cid, int status) throws SQLException 
    {
        try {    
           String sql = "UPDATE tbl_customer SET status=? WHERE cid=?";
           
           pstmt = conn.prepareStatement(sql); 
           pstmt.setInt(1, status);
           pstmt.setInt(2, cid);
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
