package com.erp.entity.sales;

/**
 * <h1>ERP Entity</h1>
 * tbl_invoice Table Entity Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2009-05-22
 */
public class TblInvoice 
{
    private int invoiceId;
    private int cid;
    private String invoiceDate;
    private String referenceNo;
    private double subtotal;
    private double vat;
    private double grandTotal;

    public TblInvoice() 
    {
    }

    public TblInvoice(int invoiceId) 
    {
        this.invoiceId = invoiceId;
    }

    public TblInvoice(int invoiceId, int cid, String invoiceDate, String referenceNo, double subtotal, double vat, double grandTotal) 
    {
        this.invoiceId = invoiceId;
        this.cid = cid;
        this.invoiceDate = invoiceDate;
        this.referenceNo = referenceNo;
        this.subtotal = subtotal;
        this.vat = vat;
        this.grandTotal = grandTotal;
    }

    public int getInvoiceId() 
    {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) 
    {
        this.invoiceId = invoiceId;
    }

    public int getCid() 
    {
        return cid;
    }

    public void setCid(int cid) 
    {
        this.cid = cid;
    }

    public String getInvoiceDate() 
    {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) 
    {
        this.invoiceDate = invoiceDate;
    }

    public String getReferenceNo() 
    {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) 
    {
        this.referenceNo = referenceNo;
    }

    public double getSubtotal() 
    {
        return subtotal;
    }

    public void setSubtotal(double subtotal) 
    {
        this.subtotal = subtotal;
    }

    public double getVat() 
    {
        return vat;
    }

    public void setVat(double vat) 
    {
        this.vat = vat;
    }

    public double getGrandTotal() 
    {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) 
    {
        this.grandTotal = grandTotal;
    }

    @Override
    public String toString() 
    {
        return "TblInvoice{" + "invoiceId=" + invoiceId + ", cid=" + cid + ", invoiceDate=" + invoiceDate + ", referenceNo=" + referenceNo + ", subtotal=" + subtotal + ", vat=" + vat + ", grandTotal=" + grandTotal + '}';
    }    
}
