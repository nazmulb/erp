<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>
        <aside class="col-md-9">
            <h1>Product Out</h1>
            <div class="right-content form-area">
                <form class="form-horizontal" id="productOutForm" role="form" action="product_request?action=product_req_outed" method="POST">
                    <div class="form-group ptop-row">
                        <div class="pull-left">
                            <label>Number:</label>
                            ${result.reqId}
                            <input type="hidden" name="req_id" value="${result.reqId}" />
                        </div>
                        <div class="pull-left">
                            <label>Date:</label>
                            ${result.reqDate}
                        </div>
                        <div class="pull-left">
                            <label>Request By:</label>
                            ${result.reqByName}
                        </div>
                        <div class="pull-left">
                            <label>Required Date:</label>
                            ${result.reqRequiredDate}
                        </div>
                    </div>
                    
                    <c:forEach var="req" items="${reqDetails}">
                        <div class="form-group pbottom-row" style="width: 85%;">    
                            <div class="col-sm-3">
                                <label>Item</label> <br />
                                ${req.productName}
                                <input type="hidden" name="pid_${req.reqDetId}" value="${req.pid}" />
                                <input type="hidden" name="recid_${req.reqDetId}" value="${req.recId}" />
                            </div>    
                            <div class="col-sm-2">
                                <label>Qty</label> <br />
                                ${req.qty}
                                <input type="hidden" name="itmqty_${req.reqDetId}" value="${req.qty}" />
                            </div>
                            <div class="checkbox col-sm-1">
                                <label>
                                    <input class="chk-class" type="checkbox" name="chk_${req.reqDetId}" value="1" onclick="addRequiredItem(${req.reqDetId})" />
                                </label>
                            </div>
                            <div class="col-sm-3">
                                <label>Out</label>
                                <input type="number" name="qty_${req.reqDetId}" value="" class="form-control" />
                            </div>
                            <div class="col-sm-3">
                                <label>Rate</label>
                                <input type="number" name="rate_${req.reqDetId}" value="" class="form-control" />
                            </div>
                        </div>
                    </c:forEach>
                        
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
        // validate the form when it is submitted
        jQuery("#productOutForm").validate();
    });
</script>