<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <aside class="col-md-9">
            <h1>User List</h1>
            <div class="right-content list-area">
                <%@include file="/WEB-INF/jspf/msg.jspf" %>
                <a class="btn btn-success btn-sm btn-add-right" href="user?action=add">Add User</a>
                <div class="clearfix"></div>
                <table width="100%" class="table-bordered">
                    <tr>
                        <th>User Name</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="result" items="${results}">
                        <tr>
                            <td>${result.uname}</td>
                            <td>${result.firstName} ${result.lastName}</td>
                            <td>${result.email}</td>
                            <td>${result.phone}</td>
                            <td>${result.status==1 ? "Active" : "Inactive"}</td>
                            <td><a class="btn btn-default btn-xs" href="user?action=active_inactive&amp;uid=${result.uid}&amp;status=${result.status==1 ? 0 : 1}">${result.status==1 ? "Inactive" : "Active"}</a> <a class="btn btn-default btn-xs" href="user?action=add&amp;uid=${result.uid}">Edit</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <%@include file="/WEB-INF/jspf/page_nav.jspf" %>
            </div>
        </aside>
        <div class="clearfix"></div>
    </div>
</section>