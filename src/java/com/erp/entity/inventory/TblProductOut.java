package com.erp.entity.inventory;

import java.util.Date;

/**
 * ProductOut Table
 */
public class TblProductOut 
{
    private Integer poutId;
    private Integer pid;
    private Integer recId;
    private Double qty;
    private Double rate;
    private Date outDate;
    
    public TblProductOut() 
    {
    }

    public TblProductOut(Integer poutId) 
    {
        this.poutId = poutId;
    }

    public TblProductOut(Integer poutId, Integer pid, Integer recId, Double qty, Double rate, Date outDate) 
    {
        this.poutId = poutId;
        this.pid = pid;
        this.recId = recId;
        this.qty = qty;
        this.rate = rate;
        this.outDate = outDate;
    }

    public Integer getPoutId() 
    {
        return poutId;
    }

    public void setPoutId(Integer poutId) 
    {
        this.poutId = poutId;
    }

    public Integer getPid() 
    {
        return pid;
    }

    public void setPid(Integer pid) 
    {
        this.pid = pid;
    }

    public Integer getRecId() 
    {
        return recId;
    }

    public void setRecId(Integer recId) 
    {
        this.recId = recId;
    }

    public Double getQty() 
    {
        return qty;
    }

    public void setQty(Double qty) 
    {
        this.qty = qty;
    }

    public Double getRate() 
    {
        return rate;
    }

    public void setRate(Double rate) 
    {
        this.rate = rate;
    }

    public Date getOutDate() 
    {
        return outDate;
    }

    public void setOutDate(Date outDate) 
    {
        this.outDate = outDate;
    }

    @Override
    public String toString() 
    {
        return "TblProductOut{" + "poutId=" + poutId + ", pid=" + pid + ", recId=" + recId + ", qty=" + qty + ", rate=" + rate + ", outDate=" + outDate + '}';
    }
}
