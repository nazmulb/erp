<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>
        <aside class="col-md-9">
            <h1>Receive Report</h1>
            <div class="right-content">
                <form class="form-horizontal" role="form" action="report" method="GET">
                    <input type="hidden" name="action" value="receive_report" />
                    <div class="form-group ptop-row" style="width: 100%;">
                        <label class="col-sm-1 control-label">Item:</label>
                        <div class="col-sm-3">
                            <select name="pid" class="form-control">
                                <option value=""></option>
                                <c:forEach var="product" items="${products}">
                                    <c:choose>
                                        <c:when test="${product.pid == pid}">
                                            <option selected="selected" value="${product.pid}">${product.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${product.pid}">${product.name}</option>
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
                    int pid = (int)request.getAttribute("pid");
                    double totalQty = 0, totalRate = 0, total = 0, grandTotal = 0;
                %>
                <% if(rs != null){ %>
                    <div class="form-group">
                        <table width="100%" class="table-bordered">
                            <tr>
                                <th>Date</th>
                                <% if(pid == 0){ %>
                                    <th>Item</th>
                                <% } %>
                                <th class="text-right">Qty</th>
                                <th class="text-right">Rate</th>
                                <th class="text-right">Total</th>
                            </tr>
                            <% 
                                while(rs.next()){ 
                                    String trndate = rs.getString("trndate");
                                    String name = rs.getString("name");
                                    double qty = rs.getDouble("itm_qty");
                                    double rate = rs.getDouble("rate");
                                    total = (qty * rate);
                                    totalQty += qty;
                                    totalRate += rate;
                                    grandTotal += total;
                            %>
                                    <tr>
                                        <td><%=trndate%></td>
                                        <% if(pid == 0){ %>
                                            <td><%=name%></td>
                                        <% } %>
                                        <td class="text-right"><%=qty%></td>
                                        <td class="text-right"><%=rate%></td>
                                        <td class="text-right"><%=total%></td>
                                    </tr> 
                            <%  } %>
                            <tr class="tbl-total">
                                <td <%=(pid==0 ? "colspan='2'" : "")%>>Total:</td>
                                <td class="text-right"><%=totalQty%></td>
                                <td class="text-right"><%=totalRate%></td>
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