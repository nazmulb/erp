package com.erp.entity.inventory;

/**
 * <h1>ERP Entity</h1>
 * tbl_product_purchase_req Table Entity Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-21 
 */
public class TblProductPurchaseReq 
{
    private int purReqId;
    private String purReqDate;
    private int purReqBy;
    private int status;
    
    public TblProductPurchaseReq() 
    {
    }

    public TblProductPurchaseReq(Integer purReqId) 
    {
        this.purReqId = purReqId;
    }
    
    public TblProductPurchaseReq(int purReqId, String purReqDate, int purReqBy, int status) 
    {
        this.purReqId = purReqId;
        this.purReqDate = purReqDate;
        this.purReqBy = purReqBy;
        this.status = status;
    }

    public int getPurReqId() 
    {
        return purReqId;
    }

    public void setPurReqId(int purReqId) 
    {
        this.purReqId = purReqId;
    }

    public String getPurReqDate() 
    {
        return purReqDate;
    }

    public void setPurReqDate(String purReqDate) 
    {
        this.purReqDate = purReqDate;
    }

    public int getPurReqBy() 
    {
        return purReqBy;
    }

    public void setPurReqBy(int purReqBy) 
    {
        this.purReqBy = purReqBy;
    }

    public int getStatus() 
    {
        return status;
    }

    public void setStatus(int status) 
    {
        this.status = status;
    }

    @Override
    public String toString() 
    {
        return "TblProductPurchaseReq{" + "purReqId=" + purReqId + ", purReqDate=" + purReqDate + ", purReqBy=" + purReqBy + ", status=" + status + '}';
    }
}
