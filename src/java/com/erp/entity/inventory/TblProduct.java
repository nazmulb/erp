package com.erp.entity.inventory;

/**
 * Product Table
 */
public class TblProduct 
{
    private Integer pid;
    private String name;
    private Double currentStock;
    private Double rate;
    private String unit;
    
    public TblProduct() 
    {
    }

    public TblProduct(Integer pid) 
    {
        this.pid = pid;
    }

    public TblProduct(Integer pid, String name, Double currentStock, Double rate, String unit) 
    {
        this.pid = pid;
        this.name = name;
        this.currentStock = currentStock;
        this.rate = rate;
        this.unit = unit;
    } 
    
    public Integer getPid() 
    {
        return pid;
    }

    public void setPid(Integer pid) 
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

    public Double getCurrentStock() 
    {
        return currentStock;
    }

    public void setCurrentStock(Double currentStock) 
    {
        this.currentStock = currentStock;
    }

    public Double getRate() 
    {
        return rate;
    }

    public void setRate(Double rate) 
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

    @Override
    public String toString() 
    {
        return "TblProduct{" + "pid=" + pid + ", name=" + name + ", currentStock=" + currentStock + ", rate=" + rate + ", unit=" + unit + '}';
    }
}
