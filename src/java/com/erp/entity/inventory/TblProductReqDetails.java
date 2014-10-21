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

    @Override
    public String toString() 
    {
        return "TblProductReqDetails{" + "reqDetId=" + reqDetId + ", qty=" + qty + ", reqId=" + reqId + ", pid=" + pid + '}';
    } 
}
