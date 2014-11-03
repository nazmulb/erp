package com.erp.model.inventory;

import com.erp.dal.MysqlConnection;
import com.erp.entity.inventory.TblProductOut;
import com.erp.entity.inventory.TblProductReq;
import com.erp.entity.inventory.TblProductReqDetails;
import java.sql.*;
import java.util.ArrayList;

/**
 * <h1>ERP Model</h1>
 * Product Request Model Class which is related to below three tables:
 * tbl_product_req, tbl_product_req_details, tbl_product_out
 * 
 * @author  nazmul.basher@gmail.com
 * @version 1.0
 * @since   2009-04-22
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
     * @param withRecId return with rec_id or not
     * @return ArrayList<TblProductReqDetails> This will return all request details.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblProductReqDetails> loadByRequestId(int reqId, boolean withRecId) throws SQLException 
    {
        ResultSet rs = null;
        ArrayList<TblProductReqDetails> list = new ArrayList<TblProductReqDetails>();
        
        try {    
           String sql = ""
                   + "SELECT prd.*, p.name, p.unit FROM tbl_product_req_details prd "
                   + "LEFT JOIN tbl_product p ON(p.pid=prd.pid) "
                   + "WHERE req_id=?";
           
           if(withRecId){
                sql = ""
                    + "SELECT prd.*, p.name, p.unit, pr.rec_id FROM tbl_product_req_details prd "
                    + "LEFT JOIN tbl_product p ON(p.pid=prd.pid) "
                    + "LEFT JOIN tbl_product_purchase_req_details pprd ON(pprd.pid=prd.pid) "
                    + "LEFT JOIN tbl_product_rec pr ON(pr.pur_req_det_id=pprd.pur_req_det_id) "                        
                    + "WHERE prd.req_id=? GROUP BY prd.req_det_id";
           }
           
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
               p.setUnit(rs.getString("unit"));
               
               if(withRecId){
                   p.setRecId(rs.getInt("rec_id"));
               }
               
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
    
    /**
     * Add or update product request details depending on id.
     * @param obj product request details object.
     * @exception SQLException On SQL error.
     */
    public void saveProductReqDetails(TblProductReqDetails obj) throws SQLException 
    {
        try {
            int id = obj.getReqDetId();
            String sql;
            
            if(id>0){ 
                sql = "UPDATE tbl_product_req_details SET req_id=?, pid=?, qty=?  WHERE req_det_id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(4, id);
            }else{
                sql = "INSERT INTO tbl_product_req_details (req_id, pid, qty) VALUES (?, ?, ?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            
            pstmt.setInt(1, obj.getReqId());
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
    
    
    /**
     * Add or update product out depending on id.
     * @param obj product out object.
     * @exception SQLException On SQL error.
     */
    public void saveProductOut(TblProductOut obj) throws SQLException 
    {
        try {
            int id = obj.getPoutId();
            String sql;
            
            if(id>0){ 
                sql = "UPDATE tbl_product_out SET pid=?, rec_id=?, req_det_id=?, qty=?, rate=?, out_date=? WHERE pout_id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(7, id);
            }else{
                sql = "INSERT INTO tbl_product_out (pid, rec_id, req_det_id, qty, rate, out_date) VALUES (?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            
            pstmt.setInt(1, obj.getPid());
            pstmt.setInt(2, obj.getRecId());
            pstmt.setInt(3, obj.getReqDetId());
            pstmt.setDouble(4, obj.getQty());
            pstmt.setDouble(5, obj.getRate());
            pstmt.setString(6, obj.getOutDate());
            
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
     * Get product out info
     * @param pid
     * @param fromDate
     * @param toDate
     * @return ResultSet This will return product out info.
     * @exception SQLException On SQL error.
     */
    public ResultSet getProductOutInfo(int pid, String fromDate, String toDate) throws SQLException 
    {
        ResultSet rs = null;
        try {    
           String sql = ""
                   + "SELECT po.pid, p.name, DATE(po.out_date) AS trndate, po.out_date AS sortdate, "
                   + ((pid>0) ? "po.qty AS itm_qty, " : "SUM(po.qty) AS itm_qty, ")
                   + "po.rate "
                   + "FROM tbl_product_out po "
                   + "LEFT JOIN tbl_product p USING ( pid ) "
                   + "WHERE "
                   + ((pid>0) ? "po.pid = ? " : "1 ")
                   + "AND DATE(po.out_date) BETWEEN ? AND ? "
                   + ((pid>0) ? "" : "GROUP BY po.pid ")
                   + "ORDER BY sortdate ASC ";
           
           pstmt = conn.prepareStatement(sql);
           if(pid>0){
               pstmt.setInt(1, pid);
               pstmt.setString(2, fromDate);
               pstmt.setString(3, toDate);
           }else{
               pstmt.setString(1, fromDate);
               pstmt.setString(2, toDate);
           }
           
           rs = pstmt.executeQuery();

        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
        }
        
        return rs;   
    }
}
