package com.erp.controller.user;

import com.erp.entity.user.TblUser;
import com.erp.model.user.UserModel;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <h1>ERP Controller</h1>
 * User Controller Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-21 
 */
@WebServlet(name="UserController",
            loadOnStartup = 1,
            urlPatterns = {"/login_process",
                           "/user"})
public class UserController extends HttpServlet 
{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        try {
            if(request.getServletPath().equals("/login_process"))
            {
                loginProcess(request, response);
            }else{
                String action = request.getParameter("action");
                action = (action==null) ? "" : action.trim();

                switch(action){
                    case "logout":
                       logout(request, response);
                    break;    

                    case "add":
                        add(request, response);
                    break;

                    case "update":

                    break;    

                    case "active_inactive":
                        activeInactive(request, response);
                    break;

                    case "details":

                    break;    

                    case "list":              
                        list(request, response);
                    break;

                    default:
                        response.sendRedirect("404.jsp");
                    break;
                }
            }
          
        } catch(IOException ie) {
            throw new IOException();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loginProcess(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            String uname = request.getParameter("uname");
            String pass  = request.getParameter("password");
            UserModel um = new UserModel();
            
            if(um.isValidUser(uname, pass)){
                HttpSession session = request.getSession(true);
                session.setAttribute("uname", uname);
                response.sendRedirect("index.jsp");
            }else{
                response.sendRedirect("login.jsp?msg_type=danger&msg=User Name or Password does not match.");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void logout(HttpServletRequest request, HttpServletResponse response)
    {
        try {
           HttpSession session = request.getSession(true);
           if(session.getAttribute("uname") != null){
                session.removeAttribute("uname");
           }
           
           response.sendRedirect("login.jsp");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void list(HttpServletRequest request, HttpServletResponse response)
    {
        int page = 1;
        int recordsPerPage = 1;
        
        try {
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            
            UserModel um = new UserModel();
            ArrayList<TblUser> results = um.load((page-1)*recordsPerPage, recordsPerPage);
            int noOfRecords = um.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("results", results);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageURL", "user?action=list");
        
            String url = "/WEB-INF/view/template/user/list.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    private void activeInactive(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int uid = (request.getParameter("uid") != null) ? Integer.parseInt(request.getParameter("uid")) : 0;
            int status = (request.getParameter("status") != null) ? Integer.parseInt(request.getParameter("status")) : 0;
            
            UserModel um = new UserModel();
            um.updateStatus(uid, status);
            response.sendRedirect("user?action=list&msg_type=success&msg=User has been successfully "+(status==1 ? "activated" : "inactivated") + ".");
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void add(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            String url = "/WEB-INF/view/template/user/add.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the class.
     *
     * @return a String containing class description
     */
    @Override
    public String getServletInfo() 
    {
        return "User Controller Class";
    }// </editor-fold>

}
