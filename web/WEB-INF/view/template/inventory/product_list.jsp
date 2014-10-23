<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <aside class="col-md-9">
            <h1>Product List</h1>
            <div class="right-content">
                <table width="70%" class="table-bordered">
                    <tr>
                        <th>Product Name</th>
                        <th>Current Stock</th>
                        <th>Current Rate</th>
                        <th>Unit</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="result" items="${results}">
                        <tr>
                            <td>${result.name}</td>
                            <td>${result.currentStock}</td>
                            <td>${result.rate}</td>
                            <td>${result.unit}</td>
                            <td><a href="#">Edit</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <%@include file="/WEB-INF/jspf/page_nav.jspf" %>
            </div>
        </aside>
        <div class="clearfix"></div>
    </div>
</section>