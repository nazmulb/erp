package com.erp.entity.inventory;

/**
 * <h1>ERP Entity</h1>
 * tbl_product_req Table Entity Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-21 
 */
public class TblProductReq 
{
    private int reqId;
    private String reqDate;
    private int reqBy;
    private int status;
    private String reqRequiredDate;

    public TblProductReq() 
    {
    }

    public TblProductReq(int reqId) 
    {
        this.reqId = reqId;
    }

    public TblProductReq(int reqId, String reqDate, int reqBy, int status, String reqRequiredDate) 
    {
        this.reqId = reqId;
        this.reqDate = reqDate;
        this.reqBy = reqBy;
        this.status = status;
        this.reqRequiredDate = reqRequiredDate;
    }
    
    public int getReqId() 
    {
        return reqId;
    }

    public void setReqId(int reqId) 
    {
        this.reqId = reqId;
    }

    public String getReqDate() 
    {
        return reqDate;
    }

    public void setReqDate(String reqDate) 
    {
        this.reqDate = reqDate;
    }

    public int getReqBy() 
    {
        return reqBy;
    }

    public void setReqBy(int reqBy) 
    {
        this.reqBy = reqBy;
    }

    public int getStatus() 
    {
        return status;
    }

    public void setStatus(int status) 
    {
        this.status = status;
    }

    public String getReqRequiredDate() 
    {
        return reqRequiredDate;
    }

    public void setReqRequiredDate(String reqRequiredDate) 
    {
        this.reqRequiredDate = reqRequiredDate;
    }

    @Override
    public String toString() 
    {
        return "TblProductReq{" + "reqId=" + reqId + ", reqDate=" + reqDate + ", reqBy=" + reqBy + ", status=" + status + ", reqRequiredDate=" + reqRequiredDate + '}';
    }    
}
