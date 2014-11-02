package com.erp.controller.sales;

import com.erp.common.Utility;
import com.erp.entity.inventory.TblProduct;
import com.erp.entity.sales.TblCustomer;
import com.erp.entity.sales.TblInvoice;
import com.erp.entity.sales.TblInvoiceDetails;
import com.erp.model.inventory.ProductModel;
import com.erp.model.sales.CustomerModel;
import com.erp.model.sales.InvoiceModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1>ERP Controller</h1>
 * Invoice Controller Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2009-05-22
 */
public class InvoiceController extends HttpServlet 
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
                
                case "deliver":
                    deliver(request, response);
                break;
                
                case "delivered":
                    delivered(request, response);
                break;
                    
                case "details":
                    details(request, response);
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
            
            InvoiceModel m = new InvoiceModel();
            ArrayList<TblInvoice> results = m.load((page-1)*recordsPerPage, recordsPerPage);
            int noOfRecords = m.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("results", results);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageURL", "invoice?action=list");
        
            String url = "/WEB-INF/view/template/sales/invoice_list.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void add(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            CustomerModel m = new CustomerModel();
            ArrayList<TblCustomer> customers = m.load(1);
            request.setAttribute("customers", customers);
            
            ProductModel pm = new ProductModel();
            ArrayList<TblProduct> products = pm.load(2);
            request.setAttribute("products", products);
            String url = "/WEB-INF/view/template/sales/invoice_add.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) 
    {/*
        try {
            
            String reqDate = (request.getParameter("req_date") != null && request.getParameter("req_date") != "") ? request.getParameter("req_date") : "";
            String reqRequiredDate = (request.getParameter("req_required_date") != null && request.getParameter("req_required_date") != "") ? request.getParameter("req_required_date") : "";
            
            TblInvoice tbl = new TblInvoice();
            tbl.setReqDate(reqDate);
            tbl.setReqBy(u.getUid());
            tbl.setStatus(0);
            tbl.setReqRequiredDate(reqRequiredDate);
            
            InvoiceModel m = new InvoiceModel();
            m.save(tbl); 
            int reqId = m.getLastInsertedId();
            
            int cntInputs = (request.getParameter("cnt_inputs") != null && request.getParameter("cnt_inputs") != "") ? Integer.parseInt(request.getParameter("cnt_inputs")) : 0;
            int pid = 0;
            double qty = 0;
            
            for(int i=1; i<=cntInputs; i++){    
                try{
                    pid = Integer.parseInt(request.getParameter("pid_"+i));
                    qty = Double.parseDouble(request.getParameter("qty_"+i));
                    if(pid>0 && qty>0){
                        TblInvoiceDetails pd = new TblInvoiceDetails();
                        pd.setReqId(reqId);
                        pd.setPid(pid);
                        pd.setQty(qty);

                        m.saveInvoiceDetails(pd);
                    }
                } catch(Exception e){
                    continue;
                }
            }
            
            response.sendRedirect("invoice?action=list&msg_type=success&msg=Invoice has beed added successfully.");
            
        } catch(Exception e){
            e.printStackTrace();
        }*/
    }
   
    private void deliver(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int id = (request.getParameter("id") != null) ? Integer.parseInt(request.getParameter("id")) : 0;
            
            if(id>0){
                InvoiceModel m = new InvoiceModel();
                TblInvoice result = m.loadById(id);
                request.setAttribute("result", result);
                
                ArrayList<TblInvoiceDetails> details = m.loadByInvoiceId(result.getInvoiceId());
                request.setAttribute("details", details);
                
                String url = "/WEB-INF/view/template/sales/invoice_deliver.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            }else{
                throw new Error("Id requied to show data.");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void delivered(HttpServletRequest request, HttpServletResponse response) 
    { /*
        try {
            Enumeration paramNames = request.getParameterNames();
            int reqId = Integer.parseInt(request.getParameter("req_id"));
            int id = 0, pid = 0, recId = 0;
            double qty = 0, rate =0;
            Date dnow = new Date();
            SimpleDateFormat fullDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            ProductRequestModel m = new ProductRequestModel();
            
            while(paramNames.hasMoreElements()) {
                String paramName = (String)paramNames.nextElement();
               
                if(paramName.startsWith("itmqty_")){
                    try{
                        id = Integer.parseInt(paramName.split("_")[1]);

                        if(Integer.parseInt(request.getParameter("chk_"+id)) == 1){
                            pid = Integer.parseInt(request.getParameter("pid_"+id));    
                            recId = Integer.parseInt(request.getParameter("recid_"+id));
                            qty = Double.parseDouble(request.getParameter("qty_"+id));
                            rate = Double.parseDouble(request.getParameter("rate_"+id));
                            if(qty>0 && rate>0){
                                // Add product in product out table.
                                TblProductOut pr = new TblProductOut();
                                pr.setPid(pid);
                                pr.setRecId(recId);
                                pr.setReqDetId(id);
                                pr.setQty(qty);
                                pr.setRate(rate);
                                pr.setOutDate(fullDateTime.format(dnow));
                                m.saveProductOut(pr);
                                
                                // Update stock in product table.
                                ProductModel tbl = new ProductModel();
                                tbl.updateCurrentStock(pid, qty, false);
                                
                                // Update qty disburse in product receive table.
                                PurchaseRequestModel prm = new PurchaseRequestModel();
                                prm.updateQtyDisburse(recId, qty);
                            }
                        }
                    } catch(Exception e){
                        continue;
                    }
                 }
            } 
            
            // Mark the request as received.
            m.updateStatus(reqId, 1);
            
            response.sendRedirect("invoice?action=list&msg_type=success&msg=Invoice has beed delivered successfully.");
            
        } catch(Exception e){
            e.printStackTrace();
        }*/
    }
    
    private void details(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int id = (request.getParameter("id") != null) ? Integer.parseInt(request.getParameter("id")) : 0;
            
            if(id>0){
                InvoiceModel m = new InvoiceModel();
                TblInvoice result = m.loadById(id);
                request.setAttribute("result", result);
                
                ArrayList<TblInvoiceDetails> details = m.loadByInvoiceId(result.getInvoiceId());
                request.setAttribute("details", details);
                
                String url = "/WEB-INF/view/template/sales/invoice_details.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            }else{
                throw new Error("Id requied to show details.");
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
        return "Invoice Controller Class";
    }// </editor-fold>

}
