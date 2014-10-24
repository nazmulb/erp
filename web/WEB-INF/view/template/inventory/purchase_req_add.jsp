<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <aside class="col-md-9">
            <h1>Purchase Request</h1>

            <div class="right-content form-area">
                <form class="form-horizontal" id="purchaseRequestForm" role="form" action="purchase_request?action=purchase_req_update" method="POST">
                    
                    <div class="form-group ptop-row">
                        <label for="txtDate" class="col-sm-1 control-label">Date:</label>
                        <div class="col-sm-3">
                            <input type="text" name="pur_req_date" value="${(result!=null) ? result.purReqDate : ''}" class="form-control" id="txtDate" required />
                        </div>
                        <div class="col-sm-2"></div>
                        <label for="txtRequestBy" class="col-sm-3 control-label">Request By:</label>
                        <div class="col-sm-3">
                            <input type="text" name="pur_req_by" value="${pageContext.session.getAttribute("uname")}" class="form-control" id="txtRequestBy" readonly />
                        </div>
                    </div>
                        
                    <div class="form-group psubmit-row">
                        <a class="btn btn-default btn-sm" href="#">Add More</a>
                    </div>
                        
                    <div class="form-group pbottom-row">    
                        <div class="col-sm-5">
                            <label>Item</label>
                            <select name="pid" class="form-control" required>
                                <option value=""></option>
                                <option value="1">Item 1</option>
                                <option value="2">Item 2</option>
                                <option value="3">Item 3</option>
                                <option value="4">Item 4</option>
                                <option value="5">Item 5</option>
                            </select>
                        </div>    
                        <div class="col-sm-4">
                            <label>Qty</label>
                            <input type="number" name="qty" value="" class="form-control" required />
                        </div>
                        <div class="col-sm-1"><a class="btn btn-danger btn-xs" href="#">Remove</a></div>    
                    </div>
                    
                    
                    
                    <div class="form-group psubmit-row">
                        <button type="submit" class="btn btn-success">Submit Request</button>
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
        jQuery("#purchaseRequestForm").validate();
    });
</script>