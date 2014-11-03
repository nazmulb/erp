package com.erp.entity.inventory;

/**
 * <h1>ERP Entity</h1>
 * tbl_product_out Table Entity Class
 * 
 * @author  nazmul.basher@gmail.com
 * @version 1.0
 * @since   2009-04-21 
 */
public class TblProductOut 
{
    private int poutId;
    private int pid;
    private int recId;
    private int reqDetId;
    private double qty;
    private double rate;
    private String outDate;
    
    public TblProductOut() 
    {
    }

    public TblProductOut(int poutId) 
    {
        this.poutId = poutId;
    }

    public TblProductOut(int poutId, int pid, int recId, int reqDetId, double qty, double rate, String outDate) 
    {
        this.poutId = poutId;
        this.pid = pid;
        this.recId = recId;
        this.reqDetId = reqDetId;
        this.qty = qty;
        this.rate = rate;
        this.outDate = outDate;
    }

    public int getPoutId() 
    {
        return poutId;
    }

    public void setPoutId(int poutId) 
    {
        this.poutId = poutId;
    }

    public int getPid() 
    {
        return pid;
    }

    public void setPid(int pid) 
    {
        this.pid = pid;
    }

    public int getRecId() 
    {
        return recId;
    }

    public void setRecId(int recId) 
    {
        this.recId = recId;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) 
    {
        this.rate = rate;
    }

    public String getOutDate() 
    {
        return outDate;
    }

    public void setOutDate(String outDate) 
    {
        this.outDate = outDate;
    }

    @Override
    public String toString() 
    {
        return "TblProductOut{" + "poutId=" + poutId + ", pid=" + pid + ", recId=" + recId + ", reqDetId=" + reqDetId + ", qty=" + qty + ", rate=" + rate + ", outDate=" + outDate + '}';
    }
}
