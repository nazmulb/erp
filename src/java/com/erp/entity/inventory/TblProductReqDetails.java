package com.erp.entity.inventory;

/**
 * ProductReqDetails Table
 */
public class TblProductReqDetails 
{
    private Integer reqDetId;
    private Integer reqId;
    private Integer pid;
    private Double qty;
    
    
    public TblProductReqDetails() 
    {
    }

    public TblProductReqDetails(Integer reqDetId) 
    {
        this.reqDetId = reqDetId;
    }

    public TblProductReqDetails(Integer reqDetId, Double qty, Integer reqId, Integer pid) 
    {
        this.reqDetId = reqDetId;
        this.qty = qty;
        this.reqId = reqId;
        this.pid = pid;
    }

    public Integer getReqDetId() 
    {
        return reqDetId;
    }

    public void setReqDetId(Integer reqDetId) 
    {
        this.reqDetId = reqDetId;
    }

    public Double getQty() 
    {
        return qty;
    }

    public void setQty(Double qty) 
    {
        this.qty = qty;
    }

    public Integer getReqId() 
    {
        return reqId;
    }

    public void setReqId(Integer reqId) 
    {
        this.reqId = reqId;
    }

    public Integer getPid() 
    {
        return pid;
    }

    public void setPid(Integer pid) 
    {
        this.pid = pid;
    }

    @Override
    public String toString() 
    {
        return "TblProductReqDetails{" + "reqDetId=" + reqDetId + ", qty=" + qty + ", reqId=" + reqId + ", pid=" + pid + '}';
    } 
}
