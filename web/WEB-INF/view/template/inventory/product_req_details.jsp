<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <aside class="col-md-9">
            <h1>Product Request Details</h1>

            <div class="right-content details-view-area">
                
                <div class="row">
                    <div class="dva-left col-md-3">Number: </div>
                    <div class="dva-right col-md-9">${result.reqId}</div>
                </div>
                
                <div class="row">
                    <div class="dva-left col-md-3">Date: </div>
                    <div class="dva-right col-md-9">${result.reqDate}</div>
                </div>
                
                <div class="row">
                    <div class="dva-left col-md-3">Request By: </div>
                    <div class="dva-right col-md-9">${result.reqByName}</div>
                </div>
                
                <div class="row">
                    <div class="dva-left col-md-3">Status: </div>
                    <div class="dva-right col-md-9">${result.status==1 ? "Issued" : "Not Issue"}</div>
                </div>
                
                <div class="row">
                    <div class="dva-left col-md-3">Required Date: </div>
                    <div class="dva-right col-md-9">${result.reqRequiredDate}</div>
                </div>
                
                <div class="row" style="margin-top:15px; margin-bottom: 15px;">
                    <table width="100%" class="table-bordered">
                        <tr>
                            <th>Item</th>
                            <th>Qty</th>
                            <th>Unit</th>
                        </tr>
                        <c:forEach var="req" items="${reqDetails}">
                            <tr>
                                <td>${req.productName}</td>
                                <td>${req.qty}</td>
                                <td>${req.unit}</td>
                            </tr> 
                        </c:forEach>
                    </table>
                </div>
                
                <div class="row">
                    <a href="product_request?action=product_req_list" class="btn btn-default">Back</a>
                </div>
                
            </div>
        </aside>

        <div class="clearfix"></div>
    </div>
</section>