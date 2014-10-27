package com.erp.entity.inventory;

/**
 * <h1>ERP Entity</h1>
 * tbl_product_req_details Table Entity Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-21 
 */
public class TblProductReqDetails 
{
    private int reqDetId;
    private int reqId;
    private int pid;
    private double qty;
    private String productName;
    private int recId;
    
    public TblProductReqDetails() 
    {
    }

    public TblProductReqDetails(int reqDetId) 
    {
        this.reqDetId = reqDetId;
    }

    public TblProductReqDetails(int reqDetId, double qty, int reqId, int pid) 
    {
        this.reqDetId = reqDetId;
        this.qty = qty;
        this.reqId = reqId;
        this.pid = pid;
    }

    public TblProductReqDetails(int reqDetId, int reqId, int pid, double qty, String productName) 
    {
        this.reqDetId = reqDetId;
        this.reqId = reqId;
        this.pid = pid;
        this.qty = qty;
        this.productName = productName;
    }

    public TblProductReqDetails(int reqDetId, int reqId, int pid, double qty, String productName, int recId) 
    {
        this.reqDetId = reqDetId;
        this.reqId = reqId;
        this.pid = pid;
        this.qty = qty;
        this.productName = productName;
        this.recId = recId;
    }
    
    
    
    public int getReqDetId() 
    {
        return reqDetId;
    }

    public void setReqDetId(int reqDetId) 
    {
        this.reqDetId = reqDetId;
    }

    public double getQty() 
    {
        return qty;
    }

    public void setQty(double qty) 
    {
        this.qty = qty;
    }

    public int getReqId() 
    {
        return reqId;
    }

    public void setReqId(int reqId) 
    {
        this.reqId = reqId;
    }

    public int getPid() 
    {
        return pid;
    }

    public void setPid(int pid) 
    {
        this.pid = pid;
    }

    public String getProductName() 
    {
        return productName;
    }

    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public int getRecId() 
    {
        return recId;
    }

    public void setRecId(int recId) 
    {
        this.recId = recId;
    }

    @Override
    public String toString() 
    {
        return "TblProductReqDetails{" + "reqDetId=" + reqDetId + ", reqId=" + reqId + ", pid=" + pid + ", qty=" + qty + ", productName=" + productName + ", recId=" + recId + '}';
    }

       
}
