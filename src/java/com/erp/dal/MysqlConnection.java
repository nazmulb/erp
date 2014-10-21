package com.erp.dal;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Data Access Layer : Mysql JDBC Connection
 */
public class MysqlConnection 
{
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DB_URL = "jdbc:mysql://localhost/erp";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "";
   
    private static MysqlConnection instance = null;
    
    protected MysqlConnection() 
    {
       // Exists only to defeat instantiation.
    }
    
    public static MysqlConnection getInstance() 
    {
       if(instance == null) {
          instance = new MysqlConnection();
       }
       
       return instance;
    }
    
    public Connection getConnection()
    {
        Connection conn = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(Exception e){
            e.printStackTrace();
        }
       
        return conn;
    }
}
