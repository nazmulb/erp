package com.erp.controller.inventory;

import com.erp.entity.inventory.TblProduct;
import com.erp.model.inventory.ProductModel;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1>ERP Controller</h1>
 * Product Controller Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-22
 */
public class ProductController extends HttpServlet {

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
            action = (action==null) ? "product_list" : action.trim();

            switch(action){
                case "product_add":
                    productAdd(request, response);
                break;

                case "product_update":

                break;    

                case "product_details":

                break;    

                case "product_list":              
                    productList(request, response);
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
    
    private void productList(HttpServletRequest request, HttpServletResponse response) 
    {
        int page = 1;
        int recordsPerPage = 1;
        
        try {
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            
            ProductModel pm = new ProductModel();
            ArrayList<TblProduct> results = pm.load((page-1)*recordsPerPage, recordsPerPage);
            int noOfRecords = pm.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("results", results);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageURL", "product?action=product_list");
        
            String url = "/WEB-INF/view/template/inventory/product_list.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void productAdd(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            String url = "/WEB-INF/view/template/inventory/product_add.jsp";
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
        return "Product Controller Class";
    }// </editor-fold>

}
