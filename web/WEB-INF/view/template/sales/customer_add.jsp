<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <aside class="col-md-9">
            <h1>${pageContext.request.getParameter('cid')==null ? 'Add' : 'Edit'} Customer</h1>

            <div class="right-content form-area">
                <form id="customerEntryForm" role="form" action="customer?action=update" method="POST">
                    <input type="hidden" name="cid" value="${pageContext.request.getParameter('cid')}" />
                    <div class="form-group">
                        <label for="txtName">Name</label>
                        <input type="text" name="name" value="${(result!=null) ? result.name : ''}" class="form-control" id="txtName" placeholder="Enter Customer Name" required />
                    </div>
                    <div class="form-group">
                        <label for="txtAddress">Address</label>
                        <input type="text" name="address" value="${(result!=null) ? result.address : ''}" class="form-control" id="txtAddress" placeholder="Enter Address" />
                    </div>
                    <div class="form-group">
                        <label for="txtEmail">Email</label>
                        <input type="email" name="email" value="${(result!=null) ? result.email : ''}" class="form-control" id="txtEmail" placeholder="Enter Email" required />
                    </div>
                    <div class="form-group">
                        <label for="txtPhone">Phone</label>
                        <input type="text" name="phone" value="${(result!=null) ? result.phone : ''}" class="form-control" id="txtPhone" placeholder="Enter Phone" required />
                    </div>
                    <a href="customer?action=list" class="btn btn-default">Cancel</a>
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
        jQuery("#customerEntryForm").validate();
    });
</script>