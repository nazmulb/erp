<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <aside class="col-md-9">
            <h1>${pageContext.request.getParameter('pid')==null ? 'Add' : 'Edit'} Product</h1>

            <div class="right-content form-area">
                <form id="productEntryForm" role="form" action="product?action=product_update" method="POST">
                    <input type="hidden" name="pid" value="${pageContext.request.getParameter('pid')}" />
                    <input type="hidden" name="product_type" value="${pageContext.request.getParameter('pid')==null ? pageContext.request.getParameter('product_type') : ((result!=null) ? result.productType : 1)}" />
                    <div class="form-group">
                        <label for="txtName">Name</label>
                        <input type="text" name="name" value="${(result!=null) ? result.name : ''}" class="form-control" id="txtName" placeholder="Enter Product Name" required />
                    </div>
                    <div class="form-group">
                        <label for="txtStock">Stock</label>
                        <input type="number" name="current_stock" value="${(result!=null) ? result.currentStock : ''}" class="form-control" id="txtStock" placeholder="Enter Stock" />
                    </div>
                    <div class="form-group">
                        <label for="txtRate">Rate</label>
                        <input type="number" name="rate" value="${(result!=null) ? result.rate : ''}" class="form-control" id="txtRate" placeholder="Enter Rate" />
                    </div>
                    <div class="form-group">
                        <label for="txtUnit">Unit</label>
                        <input type="text" name="unit" value="${(result!=null) ? result.unit : ''}" class="form-control" id="txtUnit" placeholder="Enter Unit" required />
                    </div>
                    <a href="product?action=product_list&amp;product_type=${pageContext.request.getParameter('pid')==null ? pageContext.request.getParameter('product_type') : ((result!=null) ? result.productType : 1)}" class="btn btn-default">Cancel</a>
                    <button type="submit" class="btn btn-success">Submit</button>
                </form>
            </div>
        </aside>

        <div class="clearfix"></div>
    </div>
</section>
<script type="text/javascript">
    jQuery(document).ready(function(){
        // validate the form when it is submitted
        jQuery("#productEntryForm").validate();
    });
</script>