package com.erp.controller.inventory;

import com.erp.common.Utility;
import com.erp.entity.inventory.TblProduct;
import com.erp.entity.inventory.TblProductOut;
import com.erp.entity.inventory.TblProductReq;
import com.erp.entity.inventory.TblProductReqDetails;
import com.erp.entity.user.TblUser;
import com.erp.model.inventory.ProductModel;
import com.erp.model.inventory.ProductRequestModel;
import com.erp.model.inventory.PurchaseRequestModel;
import com.erp.model.user.UserModel;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <h1>ERP Controller</h1>
 * Product Request Class
 * 
 * @author  nazmul.basher@fieldnation.com
 * @version 1.0
 * @since   2014-10-22
 */
public class ProductRequestController extends HttpServlet 
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
            action = (action==null) ? "product_req_list" : action.trim();
            
            switch(action){
                case "product_req_add":
                    productReqAdd(request, response);
                break;

                case "product_req_update":
                    productReqUpdate(request, response);
                break;
                
                case "product_req_out":
                    productReqOut(request, response);
                break;
                
                case "product_req_outed":
                    productReqOuted(request, response);
                break;
                    
                case "product_req_details":
                    productReqDetails(request, response);
                break;    

                case "product_req_list":              
                    productReqList(request, response);
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
    
    private void productReqList(HttpServletRequest request, HttpServletResponse response) 
    {
        int recordsPerPage = Utility.getRecordsPerPage();
        
        try {
            int page = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
            
            ProductRequestModel m = new ProductRequestModel();
            ArrayList<TblProductReq> results = m.load((page-1)*recordsPerPage, recordsPerPage);
            int noOfRecords = m.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("results", results);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageURL", "product_request?action=product_req_list");
        
            String url = "/WEB-INF/view/template/inventory/product_req_list.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void productReqUpdate(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            
            String reqDate = (request.getParameter("req_date") != null && request.getParameter("req_date") != "") ? request.getParameter("req_date") : "";
            String reqRequiredDate = (request.getParameter("req_required_date") != null && request.getParameter("req_required_date") != "") ? request.getParameter("req_required_date") : "";
            
            HttpSession session = request.getSession(true);
            String uname = session.getAttribute("uname").toString();
            UserModel um = new UserModel();
            TblUser u = um.loadByUserName(uname);
                    
            TblProductReq p = new TblProductReq();
            p.setReqDate(reqDate);
            p.setReqBy(u.getUid());
            p.setStatus(0);
            p.setReqRequiredDate(reqRequiredDate);
            
            ProductRequestModel pm = new ProductRequestModel();
            pm.save(p); 
            int reqId = pm.getLastInsertedId();
            
            int cntInputs = (request.getParameter("cnt_inputs") != null && request.getParameter("cnt_inputs") != "") ? Integer.parseInt(request.getParameter("cnt_inputs")) : 0;
            int pid = 0;
            double qty = 0;
            
            for(int i=1; i<=cntInputs; i++){    
                try{
                    pid = Integer.parseInt(request.getParameter("pid_"+i));
                    qty = Double.parseDouble(request.getParameter("qty_"+i));
                    if(pid>0 && qty>0){
                        TblProductReqDetails pd = new TblProductReqDetails();
                        pd.setReqId(reqId);
                        pd.setPid(pid);
                        pd.setQty(qty);

                        pm.saveProductReqDetails(pd);
                    }
                } catch(Exception e){
                    continue;
                }
            }
            
            response.sendRedirect("product_request?action=product_req_list&msg_type=success&msg=Product request has beed added successfully.");
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void productReqAdd(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            ProductModel pm = new ProductModel();
            ArrayList<TblProduct> products = pm.load(1);
            request.setAttribute("products", products);
            String url = "/WEB-INF/view/template/inventory/product_req_add.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void productReqOut(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int id = (request.getParameter("id") != null) ? Integer.parseInt(request.getParameter("id")) : 0;
            
            if(id>0){
                ProductRequestModel m = new ProductRequestModel();
                TblProductReq result = m.loadById(id);
                request.setAttribute("result", result);
                
                ArrayList<TblProductReqDetails> reqDetails = m.loadByRequestId(result.getReqId(), true);
                request.setAttribute("reqDetails", reqDetails);
                
                String url = "/WEB-INF/view/template/inventory/product_req_out.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            }else{
                throw new Error("Id requied to show data.");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void productReqOuted(HttpServletRequest request, HttpServletResponse response) 
    { 
        try {
            Enumeration paramNames = request.getParameterNames();
            int reqId = Integer.parseInt(request.getParameter("req_id"));
            int id = 0, pid = 0, recId = 0;
            double qty = 0, rate =0;
            Date dnow = new Date();
            SimpleDateFormat fullDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            ProductRequestModel pm = new ProductRequestModel();
            
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
                                pm.saveProductOut(pr);
                                
                                // Update stock in product table.
                                ProductModel p = new ProductModel();
                                p.updateCurrentStock(pid, qty, false);
                                
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
            pm.updateStatus(reqId, 1);
            
            response.sendRedirect("product_request?action=product_req_list&msg_type=success&msg=Product request has beed issued successfully.");
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void productReqDetails(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int id = (request.getParameter("id") != null) ? Integer.parseInt(request.getParameter("id")) : 0;
            
            if(id>0){
                ProductRequestModel m = new ProductRequestModel();
                TblProductReq result = m.loadById(id);
                request.setAttribute("result", result);
                
                ArrayList<TblProductReqDetails> reqDetails = m.loadByRequestId(result.getReqId(), false);
                request.setAttribute("reqDetails", reqDetails);
                
                String url = "/WEB-INF/view/template/inventory/product_req_details.jsp";
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
        return "Product Request Class";
    }// </editor-fold>

}
