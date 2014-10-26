<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>
        <aside class="col-md-9">
            <h1>Product Request</h1>
            <%  SimpleDateFormat todayDate = new SimpleDateFormat("yyyy-MM-dd"); %>
            <div class="right-content form-area">
                <form class="form-horizontal" id="productRequestForm" role="form" action="product_request?action=product_req_update" method="POST">
                    <input type="hidden" id="cntInputs" name="cnt_inputs" value="1" />
                    <div class="form-group ptop-row" style="width: 100%;">
                        <label for="txtDate" class="col-sm-1 control-label">Date:</label>
                        <div class="col-sm-3">
                            <input type="text" name="req_date" value="<%=todayDate.format(dNow)%>" class="form-control" id="txtDate" readonly />
                        </div>
                        <label for="txtRequestBy" class="col-sm-1 control-label">By:</label>
                        <div class="col-sm-2">
                            <input type="text" name="req_by" value="${pageContext.session.getAttribute("uname")}" class="form-control" id="txtRequestBy" readonly />
                        </div>
                        <label for="txtRDate" class="col-sm-2 control-label">Required:</label>
                        <div class="col-sm-3">
                            <input type="text" name="req_required_date" value="<%=todayDate.format(dNow)%>" class="form-control" id="txtRDate" readonly />
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
        jQuery("#productRequestForm").validate();
        
        jQuery("#aItemAddMore").on( "click", addMoreItem );
        
        jQuery("[name=req_date]").datetimepicker({
            timepicker:false,
            format:'Y-m-d'
        });
        
        jQuery("[name=req_required_date]").datetimepicker({
            timepicker:false,
            format:'Y-m-d'
        });
    });
</script>