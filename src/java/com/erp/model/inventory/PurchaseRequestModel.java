package com.erp.model.inventory;

import com.erp.dal.MysqlConnection;
import com.erp.entity.inventory.TblProductPurchaseReq;
import com.erp.entity.inventory.TblProductPurchaseReqDetails;
import com.erp.entity.inventory.TblProductRec;
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
           String sql = ""
                   + "SELECT ppr.*, u.uname FROM tbl_product_purchase_req ppr "
                   + "LEFT JOIN tbl_user u ON(u.uid=ppr.pur_req_by) "
                   + "WHERE pur_req_id=? LIMIT 1";
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, id);
           
           rs = pstmt.executeQuery();

           if(rs.next()){
               p.setPurReqId(rs.getInt("pur_req_id"));
               p.setPurReqDate(rs.getString("pur_req_date"));
               p.setPurReqBy(rs.getInt("pur_req_by"));
               p.setStatus(rs.getInt("status"));
               p.setPurReqByName(rs.getString("uname"));
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
     * Update status by purchase request id.
     * @param purReqId purchase request id.
     * @param status
     * @exception SQLException On SQL error.
    */
    public void updateStatus(int purReqId, int status) throws SQLException 
    {
        try {    
           String sql = "UPDATE tbl_product_purchase_req SET status=? WHERE pur_req_id=?";
           
           pstmt = conn.prepareStatement(sql); 
           pstmt.setInt(1, status);
           pstmt.setInt(2, purReqId);
           pstmt.executeUpdate();      
         
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
           String sql = ""
                   + "SELECT prd.*, p.name, p.unit FROM tbl_product_purchase_req_details prd "
                   + "LEFT JOIN tbl_product p ON(p.pid=prd.pid) "
                   + "WHERE pur_req_id=?";
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, purReqId);
           
           rs = pstmt.executeQuery();

           while(rs.next()){
               TblProductPurchaseReqDetails p = new TblProductPurchaseReqDetails();
               p.setPurReqDetId(rs.getInt("pur_req_det_id"));
               p.setPurReqId(rs.getInt("pur_req_id"));
               p.setPid(rs.getInt("pid"));
               p.setQty(rs.getDouble("qty"));
               p.setProductName(rs.getString("name"));
               p.setUnit(rs.getString("unit"));
               
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
    
    
    /**
     * Add or update product receive depending on id.
     * @param obj product receive object.
     * @exception SQLException On SQL error.
     */
    public void saveProductReceive(TblProductRec obj) throws SQLException 
    {
        try {
            int id = obj.getRecId();
            String sql;
            
            if(id>0){ 
                sql = "UPDATE tbl_product_rec SET pur_req_det_id=?, qty=?, rate=?, rec_date=?, qty_disburse=? WHERE rec_id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(6, id);
            }else{
                sql = "INSERT INTO tbl_product_rec (pur_req_det_id, qty, rate, rec_date, qty_disburse) VALUES (?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            
            pstmt.setInt(1, obj.getPurReqDetId());
            pstmt.setDouble(2, obj.getQty());
            pstmt.setDouble(3, obj.getRate());
            pstmt.setString(4, obj.getRecDate());
            pstmt.setDouble(5, obj.getQtyDisburse());
            
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
     * Update qty disburse by product receive id.
     * @param recId
     * @param qty
     * @exception SQLException On SQL error.
    */
    public void updateQtyDisburse(int recId, double qty) throws SQLException 
    {
        try {    
           String sql = "UPDATE tbl_product_rec SET qty_disburse=(qty_disburse + ?) WHERE rec_id=?";
           
           pstmt = conn.prepareStatement(sql); 
           pstmt.setDouble(1, qty);
           pstmt.setInt(2, recId);
           pstmt.executeUpdate();      
         
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
        }
    }
    
    
    /**
     * Get product ledger info
     * @param pid
     * @param fromDate
     * @param toDate
     * @return ResultSet This will return product ledger info.
     * @exception SQLException On SQL error.
     */
    public ResultSet getproductLedgerInfo(int pid, String fromDate, String toDate) throws SQLException 
    {
        ResultSet rs = null;
        try {    
           String sql = ""
                   + "(SELECT pprd.pid, DATE(pr.rec_date) AS trndate, 'received' AS trntype, pr.qty, pr.rate "
                   + "FROM tbl_product_rec pr "
                   + "INNER JOIN tbl_product_purchase_req_details pprd USING ( pur_req_det_id ) "
                   + "WHERE pprd.pid = ? AND DATE(pr.rec_date) BETWEEN ? AND ?) "
                   + "UNION "
                   + "(SELECT pid, DATE(out_date) AS trndate, 'issued' AS trntype, qty, rate "
                   + "FROM tbl_product_out "
                   + "WHERE pid = ? AND DATE(out_date) BETWEEN ? AND ? "
                   + "ORDER BY trndate) ";
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, pid);
           pstmt.setString(2, fromDate);
           pstmt.setString(3, toDate);
           pstmt.setInt(4, pid);
           pstmt.setString(5, fromDate);
           pstmt.setString(6, toDate);
           
           rs = pstmt.executeQuery();

        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
        }
        
        return rs;   
    }
    
    
    /**
     * Get opening balance
     * @param pid
     * @param fromDate
     * @return ResultSet This will return opening balance
     * @exception SQLException On SQL error.
     */
    public double getOpeningBalance(int pid, String fromDate) throws SQLException 
    {
        ResultSet rs = null;
        double balance = 0.0;
        try {    
           String sql = ""
                   + "SELECT IFNULL( SUM( IFNULL( received, 0 ) - IFNULL( issued, 0 ) ) , 0 ) AS opening_balance "
                   + "FROM ( "
                   + "(SELECT pid, tbl_product_rec.qty AS received, '0' AS issued "
                   + "FROM tbl_product_rec "
                   + "INNER JOIN tbl_product_purchase_req_details USING ( pur_req_det_id )  "
                   + "WHERE pid = ? AND DATE( rec_date ) < ?) "
                   + "UNION  "
                   + "(SELECT pid, '0' AS received, qty AS issued "
                   + "FROM tbl_product_out "
                   + "WHERE pid = ? AND DATE( out_date ) < ?) "
                   + ") AS temp ";
                   
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, pid);
           pstmt.setString(2, fromDate);
           pstmt.setInt(3, pid);
           pstmt.setString(4, fromDate);
          
           rs = pstmt.executeQuery();
           if(rs.next()){ 
               balance = rs.getDouble("opening_balance");
           }
           
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
        }
        
        return balance;
    }
}
