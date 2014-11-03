package com.erp.controller.user;

import com.erp.common.Utility;
import com.erp.entity.user.TblUser;
import com.erp.model.user.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author  nazmul.basher@gmail.com
 * @version 1.0
 * @since   2009-04-21 
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
                        update(request, response);
                    break;    

                    case "active_inactive":
                        activeInactive(request, response);
                    break;   
                        
                    case "check_uname":              
                        checkUname(request, response);
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
        int recordsPerPage = Utility.getRecordsPerPage();
        
        try {
            int page = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
            
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
            int uid = (request.getParameter("uid") != null) ? Integer.parseInt(request.getParameter("uid")) : 0;
            request.setAttribute("result", null);
            
            if(uid>0){
                UserModel pm = new UserModel();
                TblUser result = pm.loadById(uid);
                request.setAttribute("result", result);
            }
            
            String url = "/WEB-INF/view/template/user/add.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int uid = (request.getParameter("uid") != null && request.getParameter("uid") != "") ? Integer.parseInt(request.getParameter("uid")) : 0;
            String uname = (request.getParameter("uname") != null && request.getParameter("uname") != "") ? request.getParameter("uname") : "";
            String firstName = (request.getParameter("first_name") != null && request.getParameter("first_name") != "") ? request.getParameter("first_name") : "";
            String lastName = (request.getParameter("last_name") != null && request.getParameter("last_name") != "") ? request.getParameter("last_name") : "";
            String password = (request.getParameter("password") != null && request.getParameter("password") != "") ? request.getParameter("password") : "";
            String email = (request.getParameter("email") != null && request.getParameter("email") != "") ? request.getParameter("email") : "";
            String phone = (request.getParameter("phone") != null && request.getParameter("phone") != "") ? request.getParameter("phone") : "";
            int pass = 0;
            
            UserModel m = new UserModel();
            TblUser u;
            
            if(uid>0){
                u = m.loadById(uid);
            }else{
                u = new TblUser();
            }
            
            if(password != ""){
                pass = password.hashCode();
            }
            
            u.setUid(uid);
            u.setUname(uname);
            u.setFirstName(firstName);
            u.setLastName(lastName);
            if(pass != 0){
                u.setPassword(Integer.toString(pass));
            }
            u.setEmail(email);
            u.setPhone(phone);
            
            m.save(u);         
            
            response.sendRedirect("user?action=list&msg_type=success&msg=User has beed "+(uid==0 ? "added" : "updated") + " successfully.");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void checkUname(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            String uname = (request.getParameter("uname") != null && request.getParameter("uname") != "") ? request.getParameter("uname") : "";
            
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                UserModel um = new UserModel();
                int resp;
                resp = um.isExist(uname) ? 1 : 0;
                out.print(resp);
            }
            
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
