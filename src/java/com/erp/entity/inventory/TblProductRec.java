package com.erp.entity.inventory;

/**
 * <h1>ERP Entity</h1>
 * tbl_product_rec Table Entity Class
 * 
 * @author  nazmul.basher@gmail.com
 * @version 1.0
 * @since   2009-04-21 
 */
public class TblProductRec 
{
    private int recId;
    private int purReqDetId;
    private double qty;
    private double rate;
    private String recDate;
    private double qtyDisburse;
    
    public TblProductRec() 
    {
    }

    public TblProductRec(int recId) 
    {
        this.recId = recId;
    }

    public TblProductRec(int recId, int reqDetId, double qty, double rate, String recDate, double qtyDisburse) 
    {
        this.recId = recId;
        this.purReqDetId = reqDetId;
        this.qty = qty;
        this.rate = rate;
        this.recDate = recDate;
        this.qtyDisburse = qtyDisburse;
    }

    public int getRecId() 
    {
        return recId;
    }

    public void setRecId(int recId) 
    {
        this.recId = recId;
    }

    public int getPurReqDetId() 
    {
        return purReqDetId;
    }

    public void setPurReqDetId(int purReqDetId) 
    {
        this.purReqDetId = purReqDetId;
    }

    public double getQty() 
    {
        return qty;
    }

    public void setQty(double qty) 
    {
        this.qty = qty;
    }

    public double getRate() 
    {
        return rate;
    }

    public void setRate(double rate) 
    {
        this.rate = rate;
    }

    public String getRecDate() 
    {
        return recDate;
    }

    public void setRecDate(String recDate) 
    {
        this.recDate = recDate;
    }

    public double getQtyDisburse() 
    {
        return qtyDisburse;
    }

    public void setQtyDisburse(double qtyDisburse) 
    {
        this.qtyDisburse = qtyDisburse;
    }

    @Override
    public String toString() 
    {
        return "TblProductRec{" + "recId=" + recId + ", purReqDetId=" + purReqDetId + ", qty=" + qty + ", rate=" + rate + ", recDate=" + recDate + ", qtyDisburse=" + qtyDisburse + '}';
    }
}
