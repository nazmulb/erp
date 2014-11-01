package com.erp.controller.report;

import com.erp.entity.inventory.TblProduct;
import com.erp.model.inventory.ProductModel;
import com.erp.model.inventory.ProductRequestModel;
import com.erp.model.inventory.PurchaseRequestModel;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1>ERP Controller</h1>
 * Report Controller Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2009-05-22
 */
public class ReportController extends HttpServlet 
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
            action = (action==null) ? "" : action.trim();
            
            switch(action){
                case "product_ledger":
                    productLedger(request, response);
                break;

                case "receive_report":
                    receiveReport(request, response);
                break;    

                case "issue_report":              
                    issueReport(request, response);
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
    
    
    private void productLedger(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            Date dateNow = new Date();
            SimpleDateFormat todayDate = new SimpleDateFormat("yyyy-MM-dd");
            
            int pid = (request.getParameter("pid") != null && request.getParameter("pid") != "") ? Integer.parseInt(request.getParameter("pid")) : 0;
            String fromDate = (request.getParameter("from_date") != null && request.getParameter("from_date") != "") ? request.getParameter("from_date") : todayDate.format(dateNow);
            String toDate = (request.getParameter("to_date") != null && request.getParameter("to_date") != "") ? request.getParameter("to_date") : todayDate.format(dateNow);
            
            request.setAttribute("pid", pid);
            request.setAttribute("from_date", fromDate);
            request.setAttribute("to_date", toDate);
            request.setAttribute("results", null);
            request.setAttribute("balance", 0.0);
            
            ProductModel pm = new ProductModel();
            ArrayList<TblProduct> products = pm.load(1);
            request.setAttribute("products", products);
            
            if(pid>0 && fromDate!="" && toDate!=""){
                PurchaseRequestModel m = new PurchaseRequestModel();
                ResultSet results = m.getProductLedgerInfo(pid, fromDate, toDate);
                request.setAttribute("results", results);
                
                double balance = m.getOpeningBalance(pid, fromDate);
                request.setAttribute("balance", balance);
            }
            
            String url = "/WEB-INF/view/template/report/inventory_product_ledger.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void receiveReport(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            Date dateNow = new Date();
            SimpleDateFormat todayDate = new SimpleDateFormat("yyyy-MM-dd");
            
            int pid = (request.getParameter("pid") != null && request.getParameter("pid") != "") ? Integer.parseInt(request.getParameter("pid")) : 0;
            String fromDate = (request.getParameter("from_date") != null && request.getParameter("from_date") != "") ? request.getParameter("from_date") : todayDate.format(dateNow);
            String toDate = (request.getParameter("to_date") != null && request.getParameter("to_date") != "") ? request.getParameter("to_date") : todayDate.format(dateNow);
            
            request.setAttribute("pid", pid);
            request.setAttribute("from_date", fromDate);
            request.setAttribute("to_date", toDate);
            request.setAttribute("results", null);
            
            ProductModel pm = new ProductModel();
            ArrayList<TblProduct> products = pm.load(1);
            request.setAttribute("products", products);
            
            if(fromDate!="" && toDate!=""){
                PurchaseRequestModel m = new PurchaseRequestModel();
                ResultSet results = m.getReceiveInfo(pid, fromDate, toDate);
                request.setAttribute("results", results);
            }
            
            String url = "/WEB-INF/view/template/report/inventory_receive_report.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void issueReport(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            Date dateNow = new Date();
            SimpleDateFormat todayDate = new SimpleDateFormat("yyyy-MM-dd");
            
            int pid = (request.getParameter("pid") != null && request.getParameter("pid") != "") ? Integer.parseInt(request.getParameter("pid")) : 0;
            String fromDate = (request.getParameter("from_date") != null && request.getParameter("from_date") != "") ? request.getParameter("from_date") : todayDate.format(dateNow);
            String toDate = (request.getParameter("to_date") != null && request.getParameter("to_date") != "") ? request.getParameter("to_date") : todayDate.format(dateNow);
            
            request.setAttribute("pid", pid);
            request.setAttribute("from_date", fromDate);
            request.setAttribute("to_date", toDate);
            request.setAttribute("results", null);
            
            ProductModel pm = new ProductModel();
            ArrayList<TblProduct> products = pm.load(1);
            request.setAttribute("products", products);
            
            if(fromDate!="" && toDate!=""){
                ProductRequestModel m = new ProductRequestModel();
                ResultSet results = m.getProductOutInfo(pid, fromDate, toDate);
                request.setAttribute("results", results);
            }
            
            String url = "/WEB-INF/view/template/report/inventory_issue_report.jsp";
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
        return "Report Controller Class";
    }// </editor-fold>

}
