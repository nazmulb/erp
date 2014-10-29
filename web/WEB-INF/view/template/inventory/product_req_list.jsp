<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <aside class="col-md-9">
            <h1>Product Request List</h1>
            <div class="right-content list-area">
                <%@include file="/WEB-INF/jspf/msg.jspf" %>
                <a class="btn btn-success btn-sm btn-add-right" href="product_request?action=product_req_add">Add Product Request</a>
                <div class="clearfix"></div>
                <table width="100%" class="table-bordered">
                    <tr>
                        <th>Number</th>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="result" items="${results}">
                        <tr>
                            <td>${result.reqId}</td>
                            <td>${result.reqDate}</td>
                            <td>${result.status==1 ? "Issued" : "Not Issue"}</td>
                            <td>
                                <c:if test="${result.status!=1}">
                                    <a class="btn btn-default btn-xs" href="product_request?action=product_req_out&amp;id=${result.reqId}">Issue</a>
                                </c:if>
                                <a class="btn btn-default btn-xs" href="product_request?action=product_req_details&amp;id=${result.reqId}">View</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <%@include file="/WEB-INF/jspf/page_nav.jspf" %>
            </div>
        </aside>
        <div class="clearfix"></div>
    </div>
</section>