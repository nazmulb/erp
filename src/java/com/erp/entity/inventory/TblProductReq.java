package com.erp.entity.inventory;

import java.util.Date;

/**
 * ProductReq Table
 */
public class TblProductReq 
{
    private Integer reqId;
    private Date reqDate;
    private Integer reqBy;
    private Short status;
    private Date reqRequiredDate;

    public TblProductReq() 
    {
    }

    public TblProductReq(Integer reqId) 
    {
        this.reqId = reqId;
    }

    public TblProductReq(Integer reqId, Date reqDate, Integer reqBy, Short status, Date reqRequiredDate) 
    {
        this.reqId = reqId;
        this.reqDate = reqDate;
        this.reqBy = reqBy;
        this.status = status;
        this.reqRequiredDate = reqRequiredDate;
    }
    
    public Integer getReqId() 
    {
        return reqId;
    }

    public void setReqId(Integer reqId) 
    {
        this.reqId = reqId;
    }

    public Date getReqDate() 
    {
        return reqDate;
    }

    public void setReqDate(Date reqDate) 
    {
        this.reqDate = reqDate;
    }

    public Integer getReqBy() 
    {
        return reqBy;
    }

    public void setReqBy(Integer reqBy) 
    {
        this.reqBy = reqBy;
    }

    public Short getStatus() 
    {
        return status;
    }

    public void setStatus(Short status) 
    {
        this.status = status;
    }

    public Date getReqRequiredDate() 
    {
        return reqRequiredDate;
    }

    public void setReqRequiredDate(Date reqRequiredDate) 
    {
        this.reqRequiredDate = reqRequiredDate;
    }

    @Override
    public String toString() 
    {
        return "TblProductReq{" + "reqId=" + reqId + ", reqDate=" + reqDate + ", reqBy=" + reqBy + ", status=" + status + ", reqRequiredDate=" + reqRequiredDate + '}';
    }    
}
