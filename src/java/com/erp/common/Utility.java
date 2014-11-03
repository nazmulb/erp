package com.erp.common;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <h1>ERP Common</h1>
 * Utility Common Class
 * 
 * @author  nazmul.basher@gmail.com
 * @version 1.0
 * @since   2009-04-22
 */
public class Utility 
{
    private static int recordsPerPage = 50;
    
    /**
     * Authenticate the pages
     * @param request
     * @param response
     * @exception Exception
     */
    public static void auth(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            String routePath = request.getServletPath();
            
            ArrayList<String> a = new ArrayList<String>();
            a.add("/login_process");
            a.add("/login.jsp");
            a.add("/404.jsp");
            a.add("/error.jsp");

            HttpSession session = request.getSession(true);

            if(session.getAttribute("uname") == null && !a.contains(routePath))
            {
                response.sendRedirect("login.jsp");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Authenticate the pages
     * @return records per page
     * @exception Exception
     */
    public static int getRecordsPerPage()
    {
        return recordsPerPage;
    }
}
