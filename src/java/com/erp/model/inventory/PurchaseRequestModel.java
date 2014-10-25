package com.erp.model.inventory;

import com.erp.dal.MysqlConnection;
import com.erp.entity.inventory.TblProductPurchaseReq;
import com.erp.entity.inventory.TblProductPurchaseReqDetails;
import java.sql.*;
import java.util.ArrayList;

/**
 * <h1>ERP Model</h1>
 * Purchase Request Model Class which is related to below three tables:
 * tbl_product_purchase_req, tbl_product_purchase_req_details, tbl_product_rec
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-22
 */
public class PurchaseRequestModel 
{
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private int noOfRecords = 0;
    private int lastInsertedId = -1;
    
    public PurchaseRequestModel()
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
     * To get all purchase request.
     * @return ArrayList<TblProductPurchaseReq> This will return list of purchase requests.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblProductPurchaseReq> load() throws SQLException 
    {
        return load(-1, -1);
    }
    
    
    /**
     * To get purchase requests with limit.
     * @param offset
     * @param noOfRecords
     * @return ArrayList<TblProductPurchaseReq> This will return list of purchase requests.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblProductPurchaseReq> load(int offset, int noOfRecords) throws SQLException 
    {
        ResultSet rs = null;
        ArrayList<TblProductPurchaseReq> list = new ArrayList<TblProductPurchaseReq>();
        
        try {    
           String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM tbl_product_purchase_req "+((offset==-1 && noOfRecords==-1) ? "" :  " LIMIT ?, ?");
           
           pstmt = conn.prepareStatement(sql);
           if(!(offset==-1 && noOfRecords==-1)){
                pstmt.setInt(1, offset);
                pstmt.setInt(2, noOfRecords);
           }
           
           rs = pstmt.executeQuery();

           while(rs.next()){
               TblProductPurchaseReq p = new TblProductPurchaseReq();
               p.setPurReqId(rs.getInt("pur_req_id"));
               p.setPurReqDate(rs.getString("pur_req_date"));
               p.setPurReqBy(rs.getInt("pur_req_by"));
               p.setStatus(rs.getInt("status"));
               
               list.add(p);
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
        
        return list;   
    }
    
    /**
     * To get purchase request by id.
     * @param id
     * @return TblProductPurchaseReq This will return purchase request details.
     * @exception SQLException On SQL error.
     */
    public TblProductPurchaseReq loadById(int id) throws SQLException 
    {
        ResultSet rs = null;
        TblProductPurchaseReq p = new TblProductPurchaseReq();
        
        try {    
           String sql = "SELECT * FROM tbl_product_purchase_req WHERE pur_req_id=? LIMIT 1";
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, id);
           
           rs = pstmt.executeQuery();

           if(rs.next()){
               p.setPurReqId(rs.getInt("pur_req_id"));
               p.setPurReqDate(rs.getString("pur_req_date"));
               p.setPurReqBy(rs.getInt("pur_req_by"));
               p.setStatus(rs.getInt("status"));
           } 

        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
        }
        
        return p;   
    }
    
    /**
     * Add or update purchase request depending on id.
     * @param obj purchase request object.
     * @exception SQLException On SQL error.
     */
    public void save(TblProductPurchaseReq obj) throws SQLException 
    {
        try {
            int id = obj.getPurReqId();
            String sql;
            
            if(id>0){ 
                sql = "UPDATE tbl_product_purchase_req SET pur_req_date=?, pur_req_by=?, status=?  WHERE pur_req_id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(4, id);
            }else{
                sql = "INSERT INTO tbl_product_purchase_req (pur_req_date, pur_req_by, status) VALUES (?, ?, ?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            
            pstmt.setString(1, obj.getPurReqDate());
            pstmt.setInt(2, obj.getPurReqBy());
            pstmt.setInt(3, obj.getStatus());          
            pstmt.executeUpdate();
            
            if(id==0){ 
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
        }
    }
    
    /**
     * To get all purchase request details by pur_req_id.
     * @param purReqId
     * @return ArrayList<TblProductPurchaseReqDetails> This will return all purchase request details.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblProductPurchaseReqDetails> loadByPurchaseId(int purReqId) throws SQLException 
    {
        ResultSet rs = null;
        ArrayList<TblProductPurchaseReqDetails> list = new ArrayList<TblProductPurchaseReqDetails>();
        
        try {    
           String sql = "SELECT * FROM tbl_product_purchase_req_details WHERE pur_req_id=?";
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, purReqId);
           
           rs = pstmt.executeQuery();

           while(rs.next()){
               TblProductPurchaseReqDetails p = new TblProductPurchaseReqDetails();
               p.setPurReqDetId(rs.getInt("pur_req_det_id"));
               p.setPurReqId(rs.getInt("pur_req_id"));
               p.setPid(rs.getInt("pid"));
               p.setQty(rs.getDouble("qty"));
               
               list.add(p);
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
        
        return list;   
    }
    
    
    /**
     * Add or update purchase request details depending on id.
     * @param obj purchase request details object.
     * @exception SQLException On SQL error.
     */
    public void savePurchaseReqDetails(TblProductPurchaseReqDetails obj) throws SQLException 
    {
        try {
            int id = obj.getPurReqDetId();
            String sql;
            
            if(id>0){ 
                sql = "UPDATE  tbl_product_purchase_req_details SET pur_req_id=?, pid=?, qty=?  WHERE pur_req_det_id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(4, id);
            }else{
                sql = "INSERT INTO tbl_product_purchase_req_details (pur_req_id, pid, qty) VALUES (?, ?, ?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            
            pstmt.setInt(1, obj.getPurReqId());
            pstmt.setInt(2, obj.getPid());
            pstmt.setDouble(3, obj.getQty());          
            pstmt.executeUpdate();
            
            if(id==0){ 
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
        }
    }
}
