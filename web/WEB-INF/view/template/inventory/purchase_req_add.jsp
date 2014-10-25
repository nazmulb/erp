<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>
        <aside class="col-md-9">
            <h1>Purchase Request</h1>
            <%  SimpleDateFormat todayDate = new SimpleDateFormat("yyyy-MM-dd"); %>
            <div class="right-content form-area">
                <form class="form-horizontal" id="purchaseRequestForm" role="form" action="purchase_request?action=purchase_req_update" method="POST">
                    <input type="hidden" id="cntInputs" name="cnt_inputs" value="1" />
                    <div class="form-group ptop-row">
                        <label for="txtDate" class="col-sm-1 control-label">Date:</label>
                        <div class="col-sm-3">
                            <input type="text" name="pur_req_date" value="<%=todayDate.format(dNow)%>" class="form-control" id="txtDate" readonly />
                        </div>
                        <div class="col-sm-2"></div>
                        <label for="txtRequestBy" class="col-sm-3 control-label">Request By:</label>
                        <div class="col-sm-3">
                            <input type="text" name="pur_req_by" value="${pageContext.session.getAttribute("uname")}" class="form-control" id="txtRequestBy" readonly />
                        </div>
                    </div>
                        
                    <div class="form-group psubmit-row">
                        <a id="aItemAddMore" class="btn btn-default btn-sm" href="#">Add More</a>
                    </div>
                        
                    <div class="form-group pbottom-row">    
                        <div class="col-sm-5">
                            <label>Item</label>
                            <select name="pid_1" class="form-control" required>
                                <option value=""></option>
                                <c:forEach var="product" items="${products}">
                                    <option value="${product.pid}">${product.name}</option>
                                </c:forEach>
                            </select>
                        </div>    
                        <div class="col-sm-4">
                            <label>Qty</label>
                            <input type="number" name="qty_1" value="" class="form-control" required />
                        </div>
                        <div class="col-sm-1"></div>    
                    </div>
                    
                    <div id="itemToCloneDiv"></div>
                    
                    <div id="cloneDiv" style="display:none;">    
                        <div class="form-group pbottom-row">    
                            <div class="col-sm-5">
                                <label>Item</label>
                                <select name="pid_2" class="form-control">
                                    <option value=""></option>
                                    <c:forEach var="product" items="${products}">
                                        <option value="${product.pid}">${product.name}</option>
                                    </c:forEach>
                                </select>
                            </div>    
                            <div class="col-sm-4">
                                <label>Qty</label>
                                <input type="number" name="qty_2" value="" class="form-control" />
                            </div>
                            <div class="col-sm-1"><a class="btn btn-danger btn-xs" href="#" onclick="jQuery(this).parent().parent().remove()">Remove</a></div>    
                        </div>
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
        
        jQuery("#aItemAddMore").on( "click", addMoreItem );
        
        jQuery("[name=pur_req_date]").datetimepicker({
            timepicker:false,
            format:'Y-m-d'
        });
    });
</script>