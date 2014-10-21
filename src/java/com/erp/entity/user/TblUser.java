package com.erp.entity.user;

/**
 * User Table
 */
public class TblUser 
{
    private Integer uid;
    private String uname;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private String image;
    private Short status;

    public TblUser() 
    {
    }

    public TblUser(Integer uid) 
    {
        this.uid = uid;
    }

    public TblUser(Integer uid, String uname, String password, String email) 
    {
        this.uid = uid;
        this.uname = uname;
        this.password = password;
        this.email = email;
    }
 
    public Integer getUid() 
    {
        return uid;
    }

    public void setUid(Integer uid) 
    {
        this.uid = uid;
    }

    public String getUname() 
    {
        return uname;
    }

    public void setUname(String uname) 
    {
        this.uname = uname;
    }

    public String getFirstName() 
    {
        return firstName;
    }

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
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

    public String getImage() 
    {
        return image;
    }

    public void setImage(String image) 
    {
        this.image = image;
    }

    public Short getStatus() 
    {
        return status;
    }

    public void setStatus(Short status) 
    {
        this.status = status;
    }
    
    @Override
    public String toString() 
    {
        return "TblUser{" + "uid=" + uid + ", uname=" + uname + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", email=" + email + ", phone=" + phone + ", image=" + image + ", status=" + status + '}';
    }
}
