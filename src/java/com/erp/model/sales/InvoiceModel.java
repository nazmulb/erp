package com.erp.model.sales;

import com.erp.dal.MysqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
