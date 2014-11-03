package com.erp.entity.inventory;

/**
 * <h1>ERP Entity</h1>
 * tbl_product_purchase_req_details Table Entity Class
 * 
 * @author  nazmul.basher@gmail.com
 * @version 1.0
 * @since   2009-04-21 
 */
public class TblProductPurchaseReqDetails 
{
    private int purReqDetId;
    private int purReqId;
    private int pid;
    private double qty;
    private String productName;
    private String unit;
    
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

    public TblProductPurchaseReqDetails(int purReqDetId, int purReqId, int pid, double qty, String productName, String unit) 
    {
        this.purReqDetId = purReqDetId;
        this.purReqId = purReqId;
        this.pid = pid;
        this.qty = qty;
        this.productName = productName;
        this.unit = unit;
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
    
    public String getProductName() 
    {
        return productName;
    }

    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getUnit() 
    {
        return unit;
    }

    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    @Override
    public String toString() 
    {
        return "TblProductPurchaseReqDetails{" + "purReqDetId=" + purReqDetId + ", purReqId=" + purReqId + ", pid=" + pid + ", qty=" + qty + ", productName=" + productName + ", unit=" + unit + '}';
    }    
}
