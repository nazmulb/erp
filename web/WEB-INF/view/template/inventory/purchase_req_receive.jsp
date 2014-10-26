<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>
        <aside class="col-md-9">
            <h1>Purchase Request Receive</h1>
            <div class="right-content form-area">
                <form class="form-horizontal" id="purchaseRequestReceiveForm" role="form" action="purchase_request?action=purchase_req_received" method="POST">
                    <div class="form-group ptop-row">
                        <div class="pull-left">
                            <label>Number:</label>
                            ${result.purReqId}
                            <input type="hidden" name="pur_req_id" value="${result.purReqId}" />
                        </div>
                        <div class="pull-left">
                            <label>Date:</label>
                            ${result.purReqDate}
                        </div>
                        <div class="pull-left">
                            <label>Request By:</label>
                            ${result.purReqByName}
                        </div>
                    </div>
                    
                    <c:forEach var="req" items="${reqDetails}">
                        <div class="form-group pbottom-row" style="width: 85%;">    
                            <div class="col-sm-3">
                                <label>Item</label> <br />
                                ${req.productName}
                                <input type="hidden" name="pid_${req.purReqDetId}" value="${req.pid}" />
                            </div>    
                            <div class="col-sm-2">
                                <label>Qty</label> <br />
                                ${req.qty}
                                <input type="hidden" name="itmqty_${req.purReqDetId}" value="${req.qty}" />
                            </div>
                            <div class="checkbox col-sm-1">
                                <label>
                                    <input class="chk-class" type="checkbox" name="chk_${req.purReqDetId}" value="1" onclick="addRequiredItem(${req.purReqDetId})" />
                                </label>
                            </div>
                            <div class="col-sm-3">
                                <label>Receive</label>
                                <input type="number" name="qty_${req.purReqDetId}" value="" class="form-control" />
                            </div>
                            <div class="col-sm-3">
                                <label>Rate</label>
                                <input type="number" name="rate_${req.purReqDetId}" value="" class="form-control" />
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
        jQuery("#purchaseRequestReceiveForm").validate();
    });
</script>