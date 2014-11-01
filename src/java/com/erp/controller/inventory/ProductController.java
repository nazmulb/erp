package com.erp.controller.inventory;

import com.erp.common.Utility;
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
                    productUpdate(request, response);
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
        int recordsPerPage = Utility.getRecordsPerPage();
        
        try {
            int page = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
            int productType = (request.getParameter("product_type") != null) ? Integer.parseInt(request.getParameter("product_type")) : 1;
            
            ProductModel pm = new ProductModel();
            ArrayList<TblProduct> results = pm.load((page-1)*recordsPerPage, recordsPerPage, productType);
            int noOfRecords = pm.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("results", results);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("product_type", productType);
            request.setAttribute("pageURL", "product?action=product_list&product_type="+productType);
        
            String url = "/WEB-INF/view/template/inventory/product_list.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void productAdd(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int pid = (request.getParameter("pid") != null) ? Integer.parseInt(request.getParameter("pid")) : 0;
            request.setAttribute("result", null);
            
            if(pid>0){
                ProductModel pm = new ProductModel();
                TblProduct result = pm.loadById(pid);
                request.setAttribute("result", result);
            }
            
            String url = "/WEB-INF/view/template/inventory/product_add.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void productUpdate(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int pid = (request.getParameter("pid") != null && request.getParameter("pid") != "") ? Integer.parseInt(request.getParameter("pid")) : 0;
            String name = (request.getParameter("name") != null && request.getParameter("name") != "") ? request.getParameter("name") : "";
            double currentStock = (request.getParameter("current_stock") != null && request.getParameter("current_stock") != "") ? Double.parseDouble(request.getParameter("current_stock")) : 0;
            double rate = (request.getParameter("rate") != null && request.getParameter("rate") != "") ? Double.parseDouble(request.getParameter("rate")) : 0;
            String unit = (request.getParameter("unit") != null && request.getParameter("unit") != "") ? request.getParameter("unit") : "";
            int productType = (request.getParameter("product_type") != null && request.getParameter("product_type") != "") ? Integer.parseInt(request.getParameter("product_type")) : 1;
            
            TblProduct p = new TblProduct();
            p.setPid(pid);
            p.setName(name);
            p.setCurrentStock(currentStock);
            p.setRate(rate);
            p.setUnit(unit);
            p.setProductType(productType);
            
            ProductModel pm = new ProductModel();
            pm.save(p);         
            
            response.sendRedirect("product?action=product_list&product_type="+productType+"&msg_type=success&msg=Product has beed "+(pid==0 ? "added" : "updated") + " successfully.");
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
