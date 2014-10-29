package com.erp.controller.inventory;

import com.erp.common.Utility;
import com.erp.entity.inventory.TblProduct;
import com.erp.entity.inventory.TblProductPurchaseReq;
import com.erp.entity.inventory.TblProductPurchaseReqDetails;
import com.erp.entity.inventory.TblProductRec;
import com.erp.entity.user.TblUser;
import com.erp.model.inventory.ProductModel;
import com.erp.model.inventory.PurchaseRequestModel;
import com.erp.model.user.UserModel;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
                    
                case "purchase_req_receive":
                    purchaseReqReceive(request, response);
                break;
                
                case "purchase_req_received":
                    purchaseReqReceived(request, response);
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
            
            String purRqDate = (request.getParameter("pur_req_date") != null && request.getParameter("pur_req_date") != "") ? request.getParameter("pur_req_date") : "";
            
            HttpSession session = request.getSession(true);
            String uname = session.getAttribute("uname").toString();
            UserModel um = new UserModel();
            TblUser u = um.loadByUserName(uname);
                    
            TblProductPurchaseReq p = new TblProductPurchaseReq();
            p.setPurReqDate(purRqDate);
            p.setPurReqBy(u.getUid());
            p.setStatus(0);
            
            PurchaseRequestModel pm = new PurchaseRequestModel();
            pm.save(p); 
            int purReqId = pm.getLastInsertedId();
            
            int cntInputs = (request.getParameter("cnt_inputs") != null && request.getParameter("cnt_inputs") != "") ? Integer.parseInt(request.getParameter("cnt_inputs")) : 0;
            int pid = 0;
            double qty = 0;
            
            for(int i=1; i<=cntInputs; i++){
                
                try{
                    pid = Integer.parseInt(request.getParameter("pid_"+i));
                    qty = Double.parseDouble(request.getParameter("qty_"+i));
                    if(pid>0 && qty>0){
                        TblProductPurchaseReqDetails pd = new TblProductPurchaseReqDetails();
                        pd.setPurReqId(purReqId);
                        pd.setPid(pid);
                        pd.setQty(qty);

                        pm.savePurchaseReqDetails(pd);
                    }
                } catch(Exception e){
                    continue;
                }
            }
            
            response.sendRedirect("purchase_request?action=purchase_req_list&msg_type=success&msg=Purchase request has beed added successfully.");
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void purchaseReqAdd(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            ProductModel pm = new ProductModel();
            ArrayList<TblProduct> products = pm.load();
            request.setAttribute("products", products);
            String url = "/WEB-INF/view/template/inventory/purchase_req_add.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void purchaseReqReceive(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            int id = (request.getParameter("id") != null) ? Integer.parseInt(request.getParameter("id")) : 0;
            
            if(id>0){
                PurchaseRequestModel pm = new PurchaseRequestModel();
                TblProductPurchaseReq result = pm.loadById(id);
                request.setAttribute("result", result);
                
                ArrayList<TblProductPurchaseReqDetails> reqDetails = pm.loadByPurchaseId(result.getPurReqId());
                request.setAttribute("reqDetails", reqDetails);
                
                String url = "/WEB-INF/view/template/inventory/purchase_req_receive.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            }else{
                throw new Error("Id requied to receive.");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void purchaseReqReceived(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            Enumeration paramNames = request.getParameterNames();
            int purReqId = Integer.parseInt(request.getParameter("pur_req_id"));
            int id = 0, pid = 0;
            double qty = 0, rate =0;
            Date dnow = new Date();
            SimpleDateFormat fullDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            PurchaseRequestModel pm = new PurchaseRequestModel();
            
            while(paramNames.hasMoreElements()) {
                String paramName = (String)paramNames.nextElement();
               
                if(paramName.startsWith("itmqty_")){
                    try{
                        id = Integer.parseInt(paramName.split("_")[1]);

                        if(Integer.parseInt(request.getParameter("chk_"+id)) == 1){
                            pid = Integer.parseInt(request.getParameter("pid_"+id));    
                            qty = Double.parseDouble(request.getParameter("qty_"+id));
                            rate = Double.parseDouble(request.getParameter("rate_"+id));
                            if(qty>0 && rate>0){
                                // Add product in receive table.
                                TblProductRec pr = new TblProductRec();
                                pr.setPurReqDetId(id);
                                pr.setQty(qty);
                                pr.setRate(rate);
                                pr.setRecDate(fullDateTime.format(dnow));
                                pr.setQtyDisburse(0);
                                pm.saveProductReceive(pr);
                                
                                // Update stock in product table.
                                ProductModel p = new ProductModel();
                                p.updateCurrentStockAndRate(pid, qty, true, rate);
                            }
                        }
                    } catch(Exception e){
                        continue;
                    }
                 }
            } 
            
            // Mark the request as received.
            pm.updateStatus(purReqId, 1);
            
            response.sendRedirect("purchase_request?action=purchase_req_list&msg_type=success&msg=Purchase request has beed received successfully.");
            
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
                
                ArrayList<TblProductPurchaseReqDetails> reqDetails = m.loadByPurchaseId(result.getPurReqId());
                request.setAttribute("reqDetails", reqDetails);
                
                String url = "/WEB-INF/view/template/inventory/purchase_req_details.jsp";
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
        return "Purchase Request Controller Class";
    }// </editor-fold>

}
