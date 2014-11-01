package com.erp.controller.sales;

import com.erp.common.Utility;
import com.erp.entity.sales.TblCustomer;
import com.erp.model.sales.CustomerModel;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1>ERP Controller</h1>
 * Customer Controller Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2009-05-22
 */
public class CustomerController extends HttpServlet 
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
            String action = request.getParameter("action");
            action = (action==null) ? "list" : action.trim();

            switch(action){
                case "add":
                    add(request, response);
                break;

                case "update":
                    update(request, response);
                break; 
                    
                case "active_inactive":
                    activeInactive(request, response);
                break;

                case "list":              
                    list(request, response);
                break;

                default:
                    response.sendRedirect("404.jsp");
                break;
            }
        } catch(IOException ie) {
            throw new IOException();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void list(HttpServletRequest request, HttpServletResponse response) 
    {
        int recordsPerPage = Utility.getRecordsPerPage();
        
        try {
            int page = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
            
            CustomerModel m = new CustomerModel();
            ArrayList<TblCustomer> results = m.load((page-1)*recordsPerPage, recordsPerPage, -1);
            int noOfRecords = m.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("results", results);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageURL", "customer?action=list");
        
            String url = "/WEB-INF/view/template/sales/customer_list.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void add(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int cid = (request.getParameter("cid") != null) ? Integer.parseInt(request.getParameter("cid")) : 0;
            request.setAttribute("result", null);
            
            if(cid>0){
                CustomerModel pm = new CustomerModel();
                TblCustomer result = pm.loadById(cid);
                request.setAttribute("result", result);
            }
            
            String url = "/WEB-INF/view/template/sales/customer_add.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int cid = (request.getParameter("cid") != null && request.getParameter("cid") != "") ? Integer.parseInt(request.getParameter("cid")) : 0;
            String name = (request.getParameter("name") != null && request.getParameter("name") != "") ? request.getParameter("name") : "";
            String address = (request.getParameter("address") != null && request.getParameter("address") != "") ? request.getParameter("address") : "";
            String email = (request.getParameter("email") != null && request.getParameter("email") != "") ? request.getParameter("email") : "";
            String phone = (request.getParameter("phone") != null && request.getParameter("phone") != "") ? request.getParameter("phone") : "";
            int status = (request.getParameter("status") != null && request.getParameter("status") != "") ? Integer.parseInt(request.getParameter("status")) : 1;
            
            TblCustomer c = new TblCustomer();
            c.setCid(cid);
            c.setName(name);
            c.setAddress(address);
            c.setEmail(email);
            c.setPhone(phone);
            c.setStatus(status);
            
            CustomerModel m = new CustomerModel();
            m.save(c);         
            
            response.sendRedirect("customer?action=list&msg_type=success&msg=Customer has beed "+(cid==0 ? "added" : "updated") + " successfully.");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void activeInactive(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int cid = (request.getParameter("cid") != null) ? Integer.parseInt(request.getParameter("cid")) : 0;
            int status = (request.getParameter("status") != null) ? Integer.parseInt(request.getParameter("status")) : 0;
            
            CustomerModel m = new CustomerModel();
            m.updateStatus(cid, status);
            response.sendRedirect("customer?action=list&msg_type=success&msg=Customer has been successfully "+(status==1 ? "activated" : "inactivated") + ".");
            
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
        return "Customer Controller Class";
    }// </editor-fold>

}
