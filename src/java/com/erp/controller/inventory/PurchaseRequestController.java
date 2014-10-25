package com.erp.controller.inventory;

import com.erp.common.Utility;
import com.erp.entity.inventory.TblProductPurchaseReq;
import com.erp.model.inventory.PurchaseRequestModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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
                    purchaseReqAdd(request, response);
                break;

                case "purchase_req_update":
                    purchaseReqUpdate(request, response);
                break;    

                case "purchase_req_details":
                    purchaseReqDetails(request, response);
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
        int recordsPerPage = Utility.getRecordsPerPage();
        
        try {
            int page = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
            
            PurchaseRequestModel m = new PurchaseRequestModel();
            ArrayList<TblProductPurchaseReq> results = m.load((page-1)*recordsPerPage, recordsPerPage);
            int noOfRecords = m.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("results", results);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageURL", "purchase_request?action=purchase_req_list");
        
            String url = "/WEB-INF/view/template/inventory/purchase_req_list.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void purchaseReqUpdate(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            Enumeration paramNames = request.getParameterNames();

            while(paramNames.hasMoreElements()) {
               String paramName = (String)paramNames.nextElement();
               System.out.print(paramName+": ");
               String paramValue = request.getParameter(paramName);
               System.out.println(paramValue);
            }
            /*int pid = (request.getParameter("pid") != null && request.getParameter("pid") != "") ? Integer.parseInt(request.getParameter("pid")) : 0;
            String name = (request.getParameter("name") != null && request.getParameter("name") != "") ? request.getParameter("name") : "";
            double currentStock = (request.getParameter("current_stock") != null && request.getParameter("current_stock") != "") ? Double.parseDouble(request.getParameter("current_stock")) : 0;
            double rate = (request.getParameter("rate") != null && request.getParameter("rate") != "") ? Double.parseDouble(request.getParameter("rate")) : 0;
            String unit = (request.getParameter("unit") != null && request.getParameter("unit") != "") ? request.getParameter("unit") : "";
            
            TblProduct p = new TblProduct();
            p.setPid(pid);
            p.setName(name);
            p.setCurrentStock(currentStock);
            p.setRate(rate);
            p.setUnit(unit);
            
            ProductModel pm = new ProductModel();
            pm.save(p);         
            
            response.sendRedirect("product?action=product_list&msg_type=success&msg=Product has beed "+(pid==0 ? "added" : "updated") + " successfully.");
            */
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void purchaseReqAdd(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int id = (request.getParameter("id") != null) ? Integer.parseInt(request.getParameter("id")) : 0;
            request.setAttribute("result", null);
            
            if(id>0){
                PurchaseRequestModel pm = new PurchaseRequestModel();
                TblProductPurchaseReq result = pm.loadById(id);
                request.setAttribute("result", result);
            }
            
            String url = "/WEB-INF/view/template/inventory/purchase_req_add.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void purchaseReqDetails(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int id = (request.getParameter("id") != null) ? Integer.parseInt(request.getParameter("id")) : 0;
            
            if(id>0){
                PurchaseRequestModel m = new PurchaseRequestModel();
                TblProductPurchaseReq result = m.loadById(id);
                request.setAttribute("result", result);
                
                String url = "/WEB-INF/view/template/inventory/purchase_req_details.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            }else{
                throw new Error("Product id requied to show product details.");
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
        return "Purchase Request Controller Class";
    }// </editor-fold>

}
