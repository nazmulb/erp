<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <aside class="col-md-9">
            <h1>Invoice List</h1>
            <div class="right-content list-area">
                <%@include file="/WEB-INF/jspf/msg.jspf" %>
                <a class="btn btn-success btn-sm btn-add-right" href="invoice?action=add">Add Invoice</a>
                <div class="clearfix"></div>
                <table width="100%" class="table-bordered">
                    <tr>
                        <th>Invoice Number</th>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="result" items="${results}">
                        <tr>
                            <td>${result.invoiceId}</td>
                            <td>${result.invoiceDate}</td>
                            <td>${result.status==1 ? "Delivered" : "Not Deliver"}</td>
                            <td>
                                <c:if test="${result.status!=1}">
                                    <a class="btn btn-default btn-xs" href="invoice?action=deliver&amp;id=${result.invoiceId}">Deliver</a>
                                </c:if>
                                <a class="btn btn-default btn-xs" href="invoice?action=details&amp;id=${result.invoiceId}">View</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <%@include file="/WEB-INF/jspf/page_nav.jspf" %>
            </div>
        </aside>
        <div class="clearfix"></div>
    </div>
</section>