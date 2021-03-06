package com.erp.entity.sales;

/**
 * <h1>ERP Entity</h1>
 * tbl_invoice_details Table Entity Class
 * 
 * @author  nazmul.basher@gmail.com
 * @version 1.0
 * @since   2009-05-22
 */
public class TblInvoiceDetails 
{
    private int invoiceDetId;
    private int invoiceId;
    private int pid;
    private double qty;
    private double rate;
    private int status;
    private String productName;
    private String unit;

    public TblInvoiceDetails() 
    {
    }

    public TblInvoiceDetails(int invoiceDetId) 
    {
        this.invoiceDetId = invoiceDetId;
    }

    public TblInvoiceDetails(int invoiceDetId, int invoiceId, int pid, double qty, double rate, int status) 
    {
        this.invoiceDetId = invoiceDetId;
        this.invoiceId = invoiceId;
        this.pid = pid;
        this.qty = qty;
        this.rate = rate;
        this.status = status;
    }

    public TblInvoiceDetails(int invoiceDetId, int invoiceId, int pid, double qty, double rate, int status, String productName, String unit) 
    {
        this.invoiceDetId = invoiceDetId;
        this.invoiceId = invoiceId;
        this.pid = pid;
        this.qty = qty;
        this.rate = rate;
        this.status = status;
        this.productName = productName;
        this.unit = unit;
    }
    
    public int getInvoiceDetId() 
    {
        return invoiceDetId;
    }

    public void setInvoiceDetId(int invoiceDetId) 
    {
        this.invoiceDetId = invoiceDetId;
    }

    public int getInvoiceId() 
    {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) 
    {
        this.invoiceId = invoiceId;
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

    public double getRate() 
    {
        return rate;
    }

    public void setRate(double rate) 
    {
        this.rate = rate;
    }

    public int getStatus() 
    {
        return status;
    }

    public void setStatus(int status) 
    {
        this.status = status;
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
        return "TblInvoiceDetails{" + "invoiceDetId=" + invoiceDetId + ", invoiceId=" + invoiceId + ", pid=" + pid + ", qty=" + qty + ", rate=" + rate + ", status=" + status + ", productName=" + productName + ", unit=" + unit + '}';
    }
}
