<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <aside class="col-md-9">
            <h1>Purchase Request List</h1>
            <div class="right-content list-area">
                <%@include file="/WEB-INF/jspf/msg.jspf" %>
                <a class="btn btn-success btn-sm btn-add-right" href="purchase_request?action=purchase_req_add">Add Purchase Request</a>
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
                            <td>${result.purReqId}</td>
                            <td>${result.purReqDate}</td>
                            <td>${result.status==1 ? "Received" : "Not Received"}</td>
                            <td>
                                <c:if test="${result.status!=1}">
                                    <a class="btn btn-default btn-xs" href="purchase_request?action=purchase_req_receive&amp;id=${result.purReqId}">Receive</a>
                                </c:if>
                                <a class="btn btn-default btn-xs" href="purchase_request?action=purchase_req_details&amp;id=${result.purReqId}">View</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <%@include file="/WEB-INF/jspf/page_nav.jspf" %>
            </div>
        </aside>
        <div class="clearfix"></div>
    </div>
</section>