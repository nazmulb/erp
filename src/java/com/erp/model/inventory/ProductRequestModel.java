package com.erp.model.inventory;

import com.erp.dal.MysqlConnection;
import com.erp.entity.inventory.TblProductReq;
import com.erp.entity.inventory.TblProductReqDetails;
import java.sql.*;
import java.util.ArrayList;

/**
 * <h1>ERP Model</h1>
 * Product Request Model Class which is related to below three tables:
 * tbl_product_req, tbl_product_req_details, tbl_product_out
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-22
 */
public class ProductRequestModel 
{
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private int noOfRecords = 0;
    private int lastInsertedId = -1;
    
    public ProductRequestModel()
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
     * To get all product request.
     * @return ArrayList<TblProductReq> This will return list of product requests.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblProductReq> load() throws SQLException 
    {
        return load(-1, -1);
    }
    
    
    /**
     * To get product requests with limit.
     * @param offset
     * @param noOfRecords
     * @return ArrayList<TblProductReq> This will return list of product requests.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblProductReq> load(int offset, int noOfRecords) throws SQLException 
    {
        ResultSet rs = null;
        ArrayList<TblProductReq> list = new ArrayList<TblProductReq>();
        
        try {    
           String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM tbl_product_req "+((offset==-1 && noOfRecords==-1) ? "" :  " LIMIT ?, ?");
           
           pstmt = conn.prepareStatement(sql);
           if(!(offset==-1 && noOfRecords==-1)){
                pstmt.setInt(1, offset);
                pstmt.setInt(2, noOfRecords);
           }
           
           rs = pstmt.executeQuery();

           while(rs.next()){
               TblProductReq p = new TblProductReq();
               p.setReqId(rs.getInt("req_id"));
               p.setReqDate(rs.getString("req_date"));
               p.setReqBy(rs.getInt("req_by"));
               p.setStatus(rs.getInt("status"));
               p.setReqRequiredDate(rs.getString("req_required_date"));
               
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
        }
        
        return list;   
    }
    
    /**
     * To get product request by id.
     * @param id
     * @return TblProductReq This will return product request details.
     * @exception SQLException On SQL error.
     */
    public TblProductReq loadById(int id) throws SQLException 
    {
        ResultSet rs = null;
        TblProductReq p = new TblProductReq();
        
        try {    
           String sql = ""
                   + "SELECT pr.*, u.uname FROM tbl_product_req pr "
                   + "LEFT JOIN tbl_user u ON(u.uid=pr.req_by) "
                   + "WHERE req_id=? LIMIT 1";
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, id);
           
           rs = pstmt.executeQuery();

           if(rs.next()){
               p.setReqId(rs.getInt("req_id"));
               p.setReqDate(rs.getString("req_date"));
               p.setReqBy(rs.getInt("req_by"));
               p.setReqByName(rs.getString("uname"));
               p.setStatus(rs.getInt("status"));
               p.setReqRequiredDate(rs.getString("req_required_date"));
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
     * Add or update product request depending on id.
     * @param obj product request object.
     * @exception SQLException On SQL error.
     */
    public void save(TblProductReq obj) throws SQLException 
    {
        try {
            int id = obj.getReqId();
            String sql;
            
            if(id>0){ 
                sql = "UPDATE tbl_product_req SET req_date=?, req_by=?, status=?, req_required_date=?  WHERE req_id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(5, id);
            }else{
                sql = "INSERT INTO tbl_product_req (req_date, req_by, status, req_required_date) VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            
            pstmt.setString(1, obj.getReqDate());
            pstmt.setInt(2, obj.getReqBy());
            pstmt.setInt(3, obj.getStatus());          
            pstmt.setString(4, obj.getReqRequiredDate());
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
     * Update status by request id.
     * @param reqId request id.
     * @param status
     * @exception SQLException On SQL error.
    */
    public void updateStatus(int reqId, int status) throws SQLException 
    {
        try {    
           String sql = "UPDATE tbl_product_req SET status=? WHERE req_id =?";
           
           pstmt = conn.prepareStatement(sql); 
           pstmt.setInt(1, status);
           pstmt.setInt(2, reqId);
           pstmt.executeUpdate();      
         
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
        }
    }
    
    /**
     * To get all request details by req_id.
     * @param reqId
     * @return ArrayList<TblProductReqDetails> This will return all request details.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblProductReqDetails> loadByRequestId(int reqId) throws SQLException 
    {
        ResultSet rs = null;
        ArrayList<TblProductReqDetails> list = new ArrayList<TblProductReqDetails>();
        
        try {    
           String sql = ""
                   + "SELECT prd.*, p.name FROM tbl_product_req_details prd "
                   + "LEFT JOIN tbl_product p ON(p.pid=prd.pid) "
                   + "WHERE req_id=?";
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, reqId);
           
           rs = pstmt.executeQuery();

           while(rs.next()){
               TblProductReqDetails p = new TblProductReqDetails();
               p.setReqDetId(rs.getInt("req_det_id"));
               p.setReqId(rs.getInt("req_id"));
               p.setPid(rs.getInt("pid"));
               p.setQty(rs.getDouble("qty"));
               p.setProductName(rs.getString("name"));
               
               list.add(p);
           } 

        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
        }
        
        return list;   
    }
}
