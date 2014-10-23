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
     * To get all products.
     * @return ArrayList<TblProduct> This will return list of products.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblProduct> load() throws SQLException 
    {
        return load(-1, -1);
    }
    
    
    /**
     * To get products with limit.
     * @param offset
     * @param noOfRecords
     * @return ArrayList<TblProduct> This will return list of products.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblProduct> load(int offset, int noOfRecords) throws SQLException 
    {
        ResultSet rs = null;
        ArrayList<TblProduct> proList = new ArrayList<TblProduct>();
        
        try {    
           String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM tbl_product "+((offset==-1 && noOfRecords==-1) ? "" :  " LIMIT ?, ?");
           
           pstmt = conn.prepareStatement(sql);
           if(!(offset==-1 && noOfRecords==-1)){
                pstmt.setInt(1, offset);
                pstmt.setInt(2, noOfRecords);
           }
           
           rs = pstmt.executeQuery();

           while(rs.next()){
               TblProduct p = new TblProduct();
               p.setPid(rs.getInt("pid"));
               p.setName(rs.getString("name"));
               p.setCurrentStock(rs.getDouble("current_stock"));
               p.setRate(rs.getDouble("rate"));
               p.setUnit(rs.getString("unit"));
               
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
}
