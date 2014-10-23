<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <aside class="col-md-9">
            <h1>Product List</h1>
            <div class="right-content">
                <a style="margin-right: 166px; margin-bottom: 10px;" class="pull-right btn btn-success btn-sm" href="product?action=product_add">Add Product</a>
                <div class="clearfix"></div>
                <table width="80%" class="table-bordered">
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
                            <td><a class="btn btn-default btn-sm" href="product?action=product_add&amp;pid=${result.pid}">Edit</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <%@include file="/WEB-INF/jspf/page_nav.jspf" %>
            </div>
        </aside>
        <div class="clearfix"></div>
    </div>
</section>