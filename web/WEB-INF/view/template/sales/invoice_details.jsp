<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <aside class="col-md-9">
            <h1>Invoice Details</h1>

            <div class="right-content details-view-area"> 
                <div class="row">
                    <div class="dva-left col-md-3">Number: </div>
                    <div class="dva-right col-md-9">${result.invoiceId}</div>
                </div>
                <div class="row">
                    <div class="dva-left col-md-3">Customer: </div>
                    <div class="dva-right col-md-9">${result.customerName}</div>
                </div>
                
                <div class="row">
                    <div class="dva-left col-md-3">Date: </div>
                    <div class="dva-right col-md-9">${result.invoiceDate}</div>
                </div>
                <div class="row">
                    <div class="dva-left col-md-3">Reference No: </div>
                    <div class="dva-right col-md-9">${result.referenceNo} &nbsp;</div>
                </div>
                <div class="row">
                    <div class="dva-left col-md-3">Status: </div>
                    <div class="dva-right col-md-9">${result.status==1 ? "Delivered" : "Not Deliver"}</div>
                </div>
                
                <div class="row" style="margin-top:15px; margin-bottom: 15px;">
                    <table width="100%" class="table-bordered">
                        <tr>
                            <th>Item</th>
                            <th class="text-right">Qty</th>
                            <th class="text-right">Rate</th>
                            <th>Unit</th>
                            <th class="text-right">Total</th>
                        </tr>
                        <c:forEach var="row" items="${details}">
                            <tr>
                                <td>${row.productName}</td>
                                <td class="text-right">${row.qty}</td>
                                <td class="text-right">${row.rate}</td>
                                <td>${row.unit}</td>
                                <td class="text-right">${(row.qty*row.rate)}</td>
                            </tr> 
                        </c:forEach>
                        <tr class="tbl-total">
                            <td colspan="4" class="text-right">Subtotal:</td>
                            <td class="text-right">${result.subtotal}</td>
                        </tr>
                        <tr class="tbl-total">
                            <td colspan="4" class="text-right">Vat</td>
                            <td class="text-right">${result.vat}</td>
                        </tr>
                        <tr class="tbl-total">
                            <td colspan="4" class="text-right">Grand Total</td>
                            <td class="text-right">${result.grandTotal}</td>
                        </tr> 
                    </table>
                </div>
                
                <div class="row">
                    <a href="invoice?action=list" class="btn btn-default">Back</a>
                </div>
                
            </div>
        </aside>

        <div class="clearfix"></div>
    </div>
</section>