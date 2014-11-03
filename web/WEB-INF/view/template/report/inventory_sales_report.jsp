<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>
        <aside class="col-md-9">
            <h1>Sales Report</h1>
            <div class="right-content">
                <form class="form-horizontal" role="form" action="report" method="GET">
                    <input type="hidden" name="action" value="sales_report" />
                    <div class="form-group ptop-row" style="width: 100%;">
                        <label class="col-sm-1 control-label">Customer:</label>
                        <div class="col-sm-3">
                            <select name="cid" class="form-control">
                                <option value=""></option>
                                <c:forEach var="cust" items="${customers}">
                                    <c:choose>
                                        <c:when test="${cust.cid == cid}">
                                            <option selected="selected" value="${cust.cid}">${cust.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${cust.cid}">${cust.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="col-sm-2 control-label">Date From:</label>
                        <div class="col-sm-2">
                            <input type="text" name="from_date" value="${from_date}" class="form-control" readonly />
                        </div>
                        <label class="col-sm-1 control-label">To:</label>
                        <div class="col-sm-2">
                            <input type="text" name="to_date" value="${to_date}" class="form-control" readonly />
                        </div>
                        <div class="col-sm-1">
                            <button type="submit" class="btn btn-success">GO</button>
                        </div>
                    </div>
                </form>
                <% 
                    ResultSet rs = (ResultSet)request.getAttribute("results");
                    int cid = (int)request.getAttribute("cid");
                    double grandTotal = 0;
                %>
                <% if(rs != null){ %>
                    <div class="form-group">
                        <table width="100%" class="table-bordered">
                            <tr>
                                <th>Date</th>
                                <% if(cid == 0){ %>
                                    <th>Customer</th>
                                <% } %>
                                <th>Invoice Number</th>
                                <th>Status</th>
                                <th class="text-right">Total</th>
                            </tr>
                            <% 
                                while(rs.next()){ 
                                    int invoiceId = rs.getInt("invoice_id");
                                    String name = rs.getString("name");
                                    String invoiceDate = rs.getString("invoice_date");
                                    double total = rs.getDouble("grand_total");
                                    int status = rs.getInt("status");
                                    grandTotal += total;
                            %>
                                    <tr>
                                        <td><%=invoiceDate%></td>
                                        <% if(cid == 0){ %>
                                            <td><%=name%></td>
                                        <% } %>
                                        <td><%=invoiceId%></td>
                                        <td><%=(status==1 ? "Delivered" : "Not Deliver")%></td>
                                        <td class="text-right"><%=total%></td>
                                    </tr> 
                            <%  } %>
                            <tr class="tbl-total">
                                <td colspan="<%=(cid==0 ? '4' : '3')%>" class="text-right">Grand Total:</td>
                                <td class="text-right"><%=grandTotal%></td>
                            </tr>
                        </table>
                    </div>
                <% } %>
            </div>
        </aside>

        <div class="clearfix"></div>
    </div>
</section>
<script type="text/javascript">
    jQuery(document).ready(function(){
        jQuery("[name=from_date]").datetimepicker({
            timepicker:false,
            format:'Y-m-d'
        });
        
        jQuery("[name=to_date]").datetimepicker({
            timepicker:false,
            format:'Y-m-d'
        });
    });
</script>