<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>
        <aside class="col-md-9">
            <h1>Invoice Deliver</h1>
            <div class="right-content form-area">
                <form class="form-horizontal" id="invoiceDeliverForm" role="form" action="invoice?action=delivered" method="POST">
                    <div class="form-group ptop-row" style="width: 100%;">
                        <div class="pull-left" style="width: 15%;">
                            <label>Number:</label>
                            ${result.invoiceId}
                            <input type="hidden" name="invice_id" value="${result.invoiceId}" />
                        </div>
                        <div class="pull-left" style="width: 30%;">
                            <label>Customer:</label>
                            ${result.customerName}
                        </div>
                        <div class="pull-left" style="width: 25%;">
                            <label>Date:</label>
                            ${result.invoiceDate}
                        </div>
                        <div class="pull-left" style="width: 30%;">
                            <label>Reference No:</label>
                            ${result.referenceNo}
                        </div>
                    </div>
                    
                    <c:forEach var="row" items="${details}">
                        <div class="form-group pbottom-row" style="width: 100%;">
                            <div class="checkbox col-sm-1">
                                <label>
                                    <input class="chk-class" type="checkbox" name="chk_${row.invoiceDetId}" value="1" />
                                </label>
                            </div>
                            <div class="col-sm-5">
                                <label>Item</label> <br />
                                ${row.productName}
                                <input type="hidden" name="pid_${row.invoiceDetId}" value="${row.pid}" />
                            </div>    
                            <div class="col-sm-3">
                                <label>Qty</label> <br />
                                ${row.qty}
                                <input type="hidden" name="qty_${row.invoiceDetId}" value="${row.qty}" />
                            </div>                           
                            <div class="col-sm-3">
                                <label>Rate</label> <br />
                                ${row.rate}
                            </div>
                        </div>
                    </c:forEach>
                        
                    <div class="form-group pbottom-row" style="width: 100%; padding: 20px;">
                        <div class="pull-right">
                            <label>Subtotal: </label>
                            ${result.subtotal}
                        </div>

                        <div class="clearfix"></div>
                        
                        <div class="pull-right">
                            <label>Vat: </label>
                            ${result.vat}
                        </div>
                        
                        <div class="clearfix"></div>

                        <div class="pull-right">
                            <label>Grand Total: </label>
                            ${result.grandTotal}
                        </div>
                    </div>    
                        
                    <div class="form-group psubmit-row">
                        <button id="btnSubmit" type="submit" class="btn btn-success" disabled="disabled">Save</button>
                    </div>
                        
                </form>
            </div>
        </aside>

        <div class="clearfix"></div>
    </div>
</section>
<script type="text/javascript">
    jQuery(document).ready(function(){
        jQuery(".chk-class").on( "click", addRequired );
    });
</script>