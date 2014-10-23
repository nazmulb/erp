package com.erp.controller.inventory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1>ERP Controller</h1>
 * Purchase Request Controller Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-22
 */
public class PurchaseRequestController extends HttpServlet 
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
            action = (action==null) ? "purchase_req_list" : action.trim();

            switch(action){
                case "purchase_req_add":

                break;

                case "purchase_req_update":

                break;    

                case "purchase_req_details":

                break;    

                case "purchase_req_list":              
                    purchaseReqList(request, response);
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
    
    private void purchaseReqList(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            String url = "/WEB-INF/view/template/inventory/purchase_req_list.jsp";
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
        return "Purchase Request Controller Class";
    }// </editor-fold>

}
