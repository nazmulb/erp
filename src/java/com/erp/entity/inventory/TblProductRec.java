package com.erp.entity.inventory;

import java.util.Date;

/**
 * ProductRec Table
 */
public class TblProductRec 
{
    private Integer recId;
    private Integer reqDetId;
    private Double qty;
    private Double rate;
    private Date recDate;
    private Double qtyDisburse;
    
    public TblProductRec() 
    {
    }

    public TblProductRec(Integer recId) 
    {
        this.recId = recId;
    }

    public TblProductRec(Integer recId, Integer reqDetId, Double qty, Double rate, Date recDate, Double qtyDisburse) 
    {
        this.recId = recId;
        this.reqDetId = reqDetId;
        this.qty = qty;
        this.rate = rate;
        this.recDate = recDate;
        this.qtyDisburse = qtyDisburse;
    }

    public Integer getRecId() 
    {
        return recId;
    }

    public void setRecId(Integer recId) 
    {
        this.recId = recId;
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

    public Double getRate() 
    {
        return rate;
    }

    public void setRate(Double rate) 
    {
        this.rate = rate;
    }

    public Date getRecDate() 
    {
        return recDate;
    }

    public void setRecDate(Date recDate) 
    {
        this.recDate = recDate;
    }

    public Double getQtyDisburse() 
    {
        return qtyDisburse;
    }

    public void setQtyDisburse(Double qtyDisburse) 
    {
        this.qtyDisburse = qtyDisburse;
    }

    @Override
    public String toString() 
    {
        return "TblProductRec{" + "recId=" + recId + ", reqDetId=" + reqDetId + ", qty=" + qty + ", rate=" + rate + ", recDate=" + recDate + ", qtyDisburse=" + qtyDisburse + '}';
    }
}
