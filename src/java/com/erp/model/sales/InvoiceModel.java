package com.erp.model.sales;

import com.erp.dal.MysqlConnection;
import com.erp.entity.sales.TblInvoice;
import com.erp.entity.sales.TblInvoiceDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * <h1>ERP Model</h1>
 * Invoice Model Class which is related to below the table:
 * tbl_invoice, tbl_invoice_details
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2009-05-22
 */
public class InvoiceModel 
{
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private int noOfRecords = 0;
    private int lastInsertedId = -1;
    
    public InvoiceModel()
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
     * Get all invoices.
     * @return ArrayList<TblInvoice> This will return list of invoices.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblInvoice> load() throws SQLException 
    {
        return load(-1, -1);
    }
    
    
    /**
     * Get invoices with limit.
     * @param offset
     * @param noOfRecords
     * @return ArrayList<TblInvoice> This will return list of invoices.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblInvoice> load(int offset, int noOfRecords) throws SQLException 
    {
        ResultSet rs = null;
        ArrayList<TblInvoice> list = new ArrayList<TblInvoice>();
        
        try {    
           String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM tbl_invoice "+((offset==-1 && noOfRecords==-1) ? "" :  " LIMIT ?, ?");
           
           pstmt = conn.prepareStatement(sql);
           if(!(offset==-1 && noOfRecords==-1)){
                pstmt.setInt(1, offset);
                pstmt.setInt(2, noOfRecords);
           }
           
           rs = pstmt.executeQuery();

           while(rs.next()){
               TblInvoice tbl = new TblInvoice();
               tbl.setInvoiceId(rs.getInt("invoice_id"));
               tbl.setCid(rs.getInt("cid"));
               tbl.setInvoiceDate(rs.getString("invoice_date"));
               tbl.setReferenceNo(rs.getString("reference_no"));
               tbl.setSubtotal(rs.getDouble("subtotal"));
               tbl.setVat(rs.getDouble("vat"));
               tbl.setGrandTotal(rs.getDouble("grand_total"));
               tbl.setStatus(rs.getInt("status"));
               
               list.add(tbl);
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
     * Get invoice by id.
     * @param id
     * @return TblInvoice This will return invoice details.
     * @exception SQLException On SQL error.
     */
    public TblInvoice loadById(int id) throws SQLException 
    {
        ResultSet rs = null;
        TblInvoice tbl = new TblInvoice();
        
        try {    
           String sql = ""
                   + "SELECT i.*, c.name FROM tbl_invoice i "
                   + "LEFT JOIN tbl_customer c USING(cid) "
                   + "WHERE i.invoice_id=? LIMIT 1";
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, id);
           
           rs = pstmt.executeQuery();

           if(rs.next()){
               tbl.setInvoiceId(rs.getInt("invoice_id"));
               tbl.setCid(rs.getInt("cid"));
               tbl.setInvoiceDate(rs.getString("invoice_date"));
               tbl.setReferenceNo(rs.getString("reference_no"));
               tbl.setSubtotal(rs.getDouble("subtotal"));
               tbl.setVat(rs.getDouble("vat"));
               tbl.setGrandTotal(rs.getDouble("grand_total"));
               tbl.setStatus(rs.getInt("status"));
               tbl.setCustomerName(rs.getString("name"));
           } 

        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
        }
        
        return tbl;   
    }
    
    /**
     * Add or update invoice depending on id.
     * @param obj invoice object.
     * @exception SQLException On SQL error.
     */
    public void save(TblInvoice obj) throws SQLException 
    {
        try {
            int id = obj.getInvoiceId();
            String sql;
            
            if(id>0){ 
                sql = "UPDATE tbl_invoice SET cid=?, invoice_date=?, reference_no=?, subtotal=?, vat=?, grand_total=?, status=?  WHERE invoice_id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(8, id);
            }else{
                sql = "INSERT INTO tbl_invoice (cid, invoice_date, reference_no, subtotal, vat, grand_total, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            
            pstmt.setInt(1, obj.getCid());
            pstmt.setString(2, obj.getInvoiceDate());
            pstmt.setString(3, obj.getReferenceNo());          
            pstmt.setDouble(4, obj.getSubtotal());
            pstmt.setDouble(5, obj.getVat());
            pstmt.setDouble(6, obj.getGrandTotal());
            pstmt.setInt(7, obj.getStatus());
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
     * Update status by invoice id.
     * @param invoiceId invoice id.
     * @param status
     * @exception SQLException On SQL error.
    */
    public void updateStatus(int invoiceId, int status) throws SQLException 
    {
        try {    
           String sql = "UPDATE tbl_invoice SET status=? WHERE invoice_id=?";
           
           pstmt = conn.prepareStatement(sql); 
           pstmt.setInt(1, status);
           pstmt.setInt(2, invoiceId);
           pstmt.executeUpdate();      
         
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
        }
    }
    
    /**
     * Get all invoice details by invoice id.
     * @param invoiceId
     * @return ArrayList<TblInvoiceDetails> This will return all invoice details.
     * @exception SQLException On SQL error.
     */
    public ArrayList<TblInvoiceDetails> loadByInvoiceId(int invoiceId) throws SQLException 
    {
        ResultSet rs = null;
        ArrayList<TblInvoiceDetails> list = new ArrayList<TblInvoiceDetails>();
        
        try {    
           String sql = ""
                   + "SELECT tid.*, p.name, p.unit FROM tbl_invoice_details tid "
                   + "LEFT JOIN tbl_product p ON(p.pid=tid.pid) "
                   + "WHERE tid.invoice_id=?";
           
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, invoiceId);
           
           rs = pstmt.executeQuery();

           while(rs.next()){
               TblInvoiceDetails tbl = new TblInvoiceDetails();
               tbl.setInvoiceDetId(rs.getInt("invoice_det_id"));
               tbl.setInvoiceId(rs.getInt("invoice_id"));
               tbl.setPid(rs.getInt("pid"));
               tbl.setQty(rs.getDouble("qty"));
               tbl.setRate(rs.getDouble("rate"));
               tbl.setStatus(rs.getInt("status"));
               tbl.setProductName(rs.getString("name"));
               tbl.setUnit(rs.getString("unit"));
               
               list.add(tbl);
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
     * Add or update invoice details depending on id.
     * @param obj invoice details object.
     * @exception SQLException On SQL error.
     */
    public void saveInvoiceDetails(TblInvoiceDetails obj) throws SQLException 
    {
        try {
            int id = obj.getInvoiceDetId();
            String sql;
            
            if(id>0){ 
                sql = "UPDATE tbl_invoice_details SET invoice_id=?, pid=?, qty=?, rate=?  WHERE invoice_det_id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(5, id);
            }else{
                sql = "INSERT INTO tbl_invoice_details (invoice_id, pid, qty, rate) VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            
            pstmt.setInt(1, obj.getInvoiceId());
            pstmt.setInt(2, obj.getPid());
            pstmt.setDouble(3, obj.getQty());
            pstmt.setDouble(4, obj.getRate());
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
     * Update status by invoice details id.
     * @param invoiceDetId invoice id.
     * @param status
     * @exception SQLException On SQL error.
    */
    public void updateInvoiceDetailsStatus(int invoiceDetId, int status) throws SQLException 
    {
        try {    
           String sql = "UPDATE tbl_invoice_details SET status=? WHERE invoice_det_id=?";
           
           pstmt = conn.prepareStatement(sql); 
           pstmt.setInt(1, status);
           pstmt.setInt(2, invoiceDetId);
           pstmt.executeUpdate();      
         
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        } finally {
        }
    }
}
