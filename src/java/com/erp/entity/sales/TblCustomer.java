package com.erp.entity.sales;

/**
 * <h1>ERP Entity</h1>
 * tbl_customer Table Entity Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2009-05-22
 */
public class TblCustomer 
{
    private int cid;
    private String name;
    private String address;
    private String email;
    private String phone;
    private int status;

    public TblCustomer() 
    {
    }

    public TblCustomer(int cid) 
    {
        this.cid = cid;
    }

    public TblCustomer(int cid, String name, String address, String email, String phone, int status) 
    {
        this.cid = cid;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public int getCid() 
    {
        return cid;
    }

    public void setCid(int cid) 
    {
        this.cid = cid;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public int getStatus() 
    {
        return status;
    }

    public void setStatus(int status) 
    {
        this.status = status;
    }

    @Override
    public String toString() 
    {
        return "TblCustomer{" + "cid=" + cid + ", name=" + name + ", address=" + address + ", email=" + email + ", phone=" + phone + ", status=" + status + '}';
    }
 }
