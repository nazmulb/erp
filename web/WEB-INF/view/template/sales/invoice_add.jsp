<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>
        <aside class="col-md-9">
            <h1>Invoice Request</h1>
            <%  SimpleDateFormat todayDate = new SimpleDateFormat("yyyy-MM-dd"); %>
            <div class="right-content form-area">
                <form class="form-horizontal" id="invoiceForm" role="form" action="invoice?action=update" method="POST">
                    <input type="hidden" id="cntInputs" name="cnt_inputs" value="1" />
                    <div class="form-group ptop-row">
                        <div class="pull-left" style="width: 30%;">
                            <label for="txtCid">Customer:</label>
                            <select id="txtCid" name="cid" class="form-control" required>
                                <option value=""></option>
                                <c:forEach var="cust" items="${customers}">
                                    <option value="${cust.cid}">${cust.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="pull-left" style="width: 5%;">&nbsp;</div>
                        <div class="pull-left" style="width: 20%;">
                            <label for="txtDate">Date:</label>
                            <input type="text" name="invoice_date" value="<%=todayDate.format(dNow)%>" class="form-control" id="txtDate" readonly />
                        </div>
                        <div class="pull-left" style="width: 5%;">&nbsp;</div>
                        <div class="pull-left" style="width: 40%;">
                            <label for="txtRNo">Reference No:</label>
                            <input type="text" name="reference_no" value="" id="txtRNo" class="form-control" placeholder="Enter Reference No" />
                        </div>
                    </div>
                        
                    <div class="form-group psubmit-row">
                        <a id="aItemAddMore" class="btn btn-default btn-sm" href="#">Add More</a>
                    </div>
                        
                    <div class="form-group pbottom-row">    
                        <div class="col-sm-3">
                            <label>Item</label>
                            <select name="pid_1" class="form-control" required>
                                <option value=""></option>
                                <c:forEach var="product" items="${products}">
                                    <option value="${product.pid}">${product.name}</option>
                                </c:forEach>
                            </select>
                        </div>    
                        <div class="col-sm-2">
                            <label>Qty</label>
                            <input type="number" name="qty_1" value="" class="form-control update-total" required />
                        </div>
                        <div class="col-sm-3">
                            <label>Rate</label>
                            <input type="number" name="rate_1" value="" class="form-control update-total" required />
                        </div>
                        <div class="col-sm-3">
                            <label>Total</label>
                            <input type="number" name="total_1" value="" class="form-control update-total" readonly />
                        </div>
                        <div class="col-sm-1"></div>
                    </div>
                    
                    <div id="itemToCloneDiv"></div>
                    
                    <div id="cloneDiv" style="display:none;">    
                        <div class="form-group pbottom-row">    
                            <div class="col-sm-3">
                                <label>Item</label>
                                <select name="pid_2" class="form-control">
                                    <option value=""></option>
                                    <c:forEach var="product" items="${products}">
                                        <option value="${product.pid}">${product.name}</option>
                                    </c:forEach>
                                </select>
                            </div>    
                            <div class="col-sm-2">
                                <label>Qty</label>
                                <input type="number" name="qty_2" value="" class="form-control update-total" />
                            </div>
                            <div class="col-sm-3">
                                <label>Rate</label>
                                <input type="number" name="rate_2" value="" class="form-control update-total" />
                            </div>
                            <div class="col-sm-3">
                                <label>Total</label>
                                <input type="number" name="total_2" value="" class="form-control update-total" readonly />
                            </div>
                            <div class="col-sm-1"><a class="btn btn-danger btn-xs" href="#" onclick="jQuery(this).parent().parent().remove()">X</a></div>    
                        </div>
                    </div>
                    
                    <div class="form-group pbottom-row pbottom-row-subtotal">
                        <div class="pull-right">
                            <label>Subtotal</label>
                            <input type="number" name="subtotal" value="" class="form-control" readonly />
                        </div>

                        <div class="clearfix"></div>
                        
                        <div class="pull-right">
                            <label>Vat</label>
                            <input style="width: 70px; margin-right: 10px;" max="100" type="number" name="vat_percent" value="" class="form-control update-total" placeholder="%" />
                            <input type="text" name="vat" value="0" class="form-control" readonly />
                        </div>
                        
                        <div class="clearfix"></div>

                        <div class="pull-right">
                            <label>Grand Total</label>
                            <input type="number" name="grand_total" value="" class="form-control" required  />
                        </div>
                    </div>
                    
                    <div class="form-group psubmit-row">
                        <button type="submit" class="btn btn-success">Submit</button>
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
        jQuery("#invoiceForm").validate();
        
        jQuery("#aItemAddMore").on( "click", addMoreItem );
        
        jQuery(document).delegate( ".update-total", "blur", function() {
            updateTotal();
        });
        
        jQuery("[name=invoice_date]").datetimepicker({
            timepicker:false,
            format:'Y-m-d'
        });
    });
    
   
   
</script>