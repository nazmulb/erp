package com.erp.entity.user;

/**
 * <h1>ERP Entity</h1>
 * tbl_user Table Entity Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-21 
 */
public class TblUser 
{
    private int uid;
    private String uname;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private String image;
    private int status;

    public TblUser() 
    {
    }

    public TblUser(int uid) 
    {
        this.uid = uid;
    }

    public TblUser(int uid, String uname, String password, String email) 
    {
        this.uid = uid;
        this.uname = uname;
        this.password = password;
        this.email = email;
    }
 
    public int getUid() 
    {
        return uid;
    }

    public void setUid(int uid) 
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
        return "TblUser{" + "uid=" + uid + ", uname=" + uname + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", email=" + email + ", phone=" + phone + ", image=" + image + ", status=" + status + '}';
    }
}
