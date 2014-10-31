package com.erp.entity.inventory;

/**
 * <h1>ERP Entity</h1>
 * tbl_product Table Entity Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-21 
 */
public class TblProduct 
{
    private int pid;
    private String name;
    private double currentStock;
    private double rate;
    private String unit;
    private int productType;
    
    public TblProduct() 
    {
    }
    
    public TblProduct(int pid) 
    {
        this.pid = pid;
    }

    public TblProduct(int pid, String name, double currentStock, double rate, String unit, int productType) 
    {
        this.pid = pid;
        this.name = name;
        this.currentStock = currentStock;
        this.rate = rate;
        this.unit = unit;
        this.productType = productType;
    }
    
    public int getPid() 
    {
        return pid;
    }

    public void setPid(int pid) 
    {
        this.pid = pid;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public double getCurrentStock() 
    {
        return currentStock;
    }

    public void setCurrentStock(double currentStock) 
    {
        this.currentStock = currentStock;
    }

    public double getRate() 
    {
        return rate;
    }

    public void setRate(double rate) 
    {
        this.rate = rate;
    }

    public String getUnit() 
    {
        return unit;
    }

    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public int getProductType() 
    {
        return productType;
    }

    public void setProductType(int productType) 
    {
        this.productType = productType;
    }

    @Override
    public String toString() 
    {
        return "TblProduct{" + "pid=" + pid + ", name=" + name + ", currentStock=" + currentStock + ", rate=" + rate + ", unit=" + unit + ", productType=" + productType + '}';
    }
}
