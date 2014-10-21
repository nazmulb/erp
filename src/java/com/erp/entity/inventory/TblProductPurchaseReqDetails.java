package com.erp.entity.inventory;

/**
 * <h1>ERP Entity</h1>
 * tbl_product_purchase_req_details Table Entity Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-21 
 */
public class TblProductPurchaseReqDetails 
{
    private int purReqDetId;
    private int purReqId;
    private int pid;
    private double qty;
    
    public TblProductPurchaseReqDetails() 
    {
    }

    public TblProductPurchaseReqDetails(Integer purReqDetId) 
    {
        this.purReqDetId = purReqDetId;
    }

    public TblProductPurchaseReqDetails(int purReqDetId, int purReqId, int pid, double qty) 
    {
        this.purReqDetId = purReqDetId;
        this.purReqId = purReqId;
        this.pid = pid;
        this.qty = qty;
    }

    public int getPurReqDetId() 
    {
        return purReqDetId;
    }

    public void setPurReqDetId(int purReqDetId) 
    {
        this.purReqDetId = purReqDetId;
    }

    public int getPurReqId() 
    {
        return purReqId;
    }

    public void setPurReqId(int purReqId) 
    {
        this.purReqId = purReqId;
    }

    public int getPid() 
    {
        return pid;
    }

    public void setPid(int pid) 
    {
        this.pid = pid;
    }

    public double getQty() 
    {
        return qty;
    }

    public void setQty(double qty) 
    {
        this.qty = qty;
    }

    @Override
    public String toString() 
    {
        return "TblProductPurchaseReqDetails{" + "purReqDetId=" + purReqDetId + ", purReqId=" + purReqId + ", pid=" + pid + ", qty=" + qty + '}';
    }
}
