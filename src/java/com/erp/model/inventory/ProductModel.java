package com.erp.model.inventory;

import com.erp.dal.MysqlConnection;
import com.erp.entity.inventory.TblProduct;
import java.sql.*;
import java.util.ArrayList;

/**
 * <h1>ERP Model</h1>
 * Product Model Class which is related to below the table:
 * tbl_product
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-21 
 */
public class ProductModel 
{
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private int noOfRecords = 0;
    private int lastInsertedId = -1;
    
    public ProductModel()
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
     * To get all products.
     * @param productType
     * @return ArrayList<TblProduct> This will return list of products.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblProduct> load(int productType) throws SQLException 
    {
        return load(-1, -1, productType);
    }
    
    
    /**
     * To get products with limit.
     * @param offset
     * @param noOfRecords
     * @param productType
     * @return ArrayList<TblProduct> This will return list of products.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblProduct> load(int offset, int noOfRecords, int productType) throws SQLException 
    {
        ResultSet rs = null;
        ArrayList<TblProduct> proList = new ArrayList<TblProduct>();
        
        try {    
           String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM tbl_product WHERE product_type = ? "+((offset==-1 && noOfRecords==-1) ? "" :  " LIMIT ?, ?");
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, productType);
           
           if(!(offset==-1 && noOfRecords==-1)){
                pstmt.setInt(2, offset);
                pstmt.setInt(3, noOfRecords);
           }
           
           rs = pstmt.executeQuery();

           while(rs.next()){
               TblProduct p = new TblProduct();
               p.setPid(rs.getInt("pid"));
               p.setName(rs.getString("name"));
               p.setCurrentStock(rs.getDouble("current_stock"));
               p.setRate(rs.getDouble("rate"));
               p.setUnit(rs.getString("unit"));
               p.setProductType(rs.getInt("product_type"));
               
               proList.add(p);
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
        
        return proList;   
    }
    
    /**
     * To get product by product id.
     * @param pid
     * @return TblProduct This will return product details.
     * @exception SQLException On SQL error.
     */
    public TblProduct loadById(int pid) throws SQLException 
    {
        ResultSet rs = null;
        TblProduct p = new TblProduct();
        
        try {    
           String sql = "SELECT * FROM tbl_product WHERE pid=?";
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, pid);
           
           rs = pstmt.executeQuery();

           if(rs.next()){
               p.setPid(rs.getInt("pid"));
               p.setName(rs.getString("name"));
               p.setCurrentStock(rs.getDouble("current_stock"));
               p.setRate(rs.getDouble("rate"));
               p.setUnit(rs.getString("unit"));
               p.setProductType(rs.getInt("product_type"));
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
        
        return p;   
    }
    
    /**
     * Add or update product depending on product id.
     * @param product product object.
     * @exception SQLException On SQL error.
     */
    public void save(TblProduct product) throws SQLException 
    {
        try {
            int pid = product.getPid();
            String sql;
            
            if(pid>0){ 
                sql = "UPDATE tbl_product SET name=?, current_stock=?, rate=?, unit=?, product_type=?  WHERE pid=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(6, pid);
            }else{
                sql = "INSERT INTO tbl_product (name, current_stock, rate, unit, product_type) VALUES (?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getCurrentStock());
            pstmt.setDouble(3, product.getRate());
            pstmt.setString(4, product.getUnit());
            pstmt.setInt(5, product.getProductType());
            pstmt.executeUpdate();
            
            if(pid==0){ 
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
     * Update current stock by product id.
     * @param pid product id.
     * @param stock
     * @param increase is increase or decrease
     * @exception SQLException On SQL error.
    */
    public void updateCurrentStock(int pid, double stock, boolean increase) throws SQLException 
    {
        updateCurrentStockAndRate(pid, stock, increase, 0);
    }
    
    /**
     * Update current stock and rate by product id.
     * @param pid product id.
     * @param stock
     * @param increase is increase or decrease
     * @param rate
     * @exception SQLException On SQL error.
    */
    public void updateCurrentStockAndRate(int pid, double stock, boolean increase, double rate) throws SQLException 
    {
        try {    
            
           String opt = (increase)? "+" : "-";
           String sql = "UPDATE tbl_product SET current_stock=(current_stock "+opt+" ?) ";
           sql+=(rate>0 ? ", rate=? " : "");
           sql +="WHERE pid=?";
           
           pstmt = conn.prepareStatement(sql); 
           pstmt.setDouble(1, stock);
           if(rate>0){
               pstmt.setDouble(2, rate);
               pstmt.setInt(3, pid);
           }else{
               pstmt.setInt(2, pid);
           }
           
           pstmt.executeUpdate();      
         
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
        }
    }
}
