<%@page import="java.sql.ResultSet"%>
<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>
        <aside class="col-md-9">
            <h1>Product Ledger</h1>
            <div class="right-content">
                <form class="form-horizontal" id="productLedgerForm" role="form" action="report" method="GET">
                    <input type="hidden" name="action" value="product_ledger" />
                    <div class="form-group ptop-row" style="width: 100%;">
                        <label class="col-sm-1 control-label">Item:</label>
                        <div class="col-sm-3">
                            <select name="pid" class="form-control" required>
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
                    double balance = (double)request.getAttribute("balance");
                    double totalRec = 0, totalIssued = 0;
                %>
                <% if(rs != null){ %>
                    <div class="form-group">
                        <table width="100%" class="table-bordered">
                            <tr>
                                <th>Date</th>
                                <th>Transaction Type</th>
                                <th>Receive</th>
                                <th>Issue</th>
                                <th>Balance</th>
                            </tr>
                            <tr>
                                <td colspan="4">Opening Balance</td>
                                <td align="right"><%=balance%></td>
                            </tr>
                            <% 
                                while(rs.next()){ 
                                    String trndate = rs.getString("trndate");
                                    String trntype = rs.getString("trntype");
                                    double qty = rs.getDouble("qty");
                                    if(trntype.equals("received")){
                                        balance += qty;
                                        totalRec += qty;
                                    }
                                    
                                    if(trntype.equals("issued")){
                                        balance -= qty;
                                        totalIssued += qty;
                                    }
                                    
                            %>
                                    <tr>
                                        <td><%=trndate%></td>
                                        <td><%=trntype%></td>
                                        <td align="right"><%=(trntype.equals("received") ? qty : "")%></td>
                                        <td align="right"><%=(trntype.equals("issued") ? qty : "")%></td>
                                        <td align="right"><%=balance%></td>
                                    </tr> 
                            <%  } %>
                            <tr class="tbl-total">
                                <td colspan="2">Total:</td>
                                <td align="right"><%=totalRec%></td>
                                <td align="right"><%=totalIssued%></td>
                                <td align="right"><%=balance%></td>
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
        // validate the form when it is submitted
        jQuery("#productLedgerForm").validate();
        
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