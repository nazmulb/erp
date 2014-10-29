<section>
    <div class="container content">
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <aside class="col-md-9">
            <h1>${pageContext.request.getParameter('uid')==null ? 'Add' : 'Edit'} User</h1>

            <div class="right-content form-area">
                <form id="userForm" role="form" action="user?action=update" method="POST">
                    <input type="hidden" name="uid" value="${pageContext.request.getParameter('uid')}" />
                    <div class="form-group">
                        <label for="txtUname">User Name</label>
                        <input type="text" name="uname" value="${(result!=null) ? result.uname : ''}" class="form-control" id="txtUname" placeholder="Enter User Name" ${pageContext.request.getParameter('uid')==null ? 'required' : 'readonly'} />
                    </div>
                    <div class="form-group">
                        <label for="txtFname">First Name</label>
                        <input type="text" name="first_name" value="${(result!=null) ? result.firstName : ''}" class="form-control" id="txtFname" placeholder="Enter First Name" required />
                    </div>
                    <div class="form-group">
                        <label for="txtLname">Last Name</label>
                        <input type="text" name="last_name" value="${(result!=null) ? result.lastName : ''}" class="form-control" id="txtLname" placeholder="Enter Last Name" required />
                    </div>
                    <div class="form-group">
                        <label for="txtPassword">Password</label>
                        <input type="text" name="password" value="" class="form-control" id="txtPassword" placeholder="Enter Password" ${pageContext.request.getParameter('uid')==null ? 'required' : ''} />
                    </div>
                    <div class="form-group">
                        <label for="txtEmail">Email</label>
                        <input type="email" name="email" value="${(result!=null) ? result.email : ''}" class="form-control" id="txtEmail" placeholder="Enter Email" required />
                    </div>
                    <div class="form-group">
                        <label for="txtPhone">Phone</label>
                        <input type="text" name="phone" value="${(result!=null) ? result.phone : ''}" class="form-control" id="txtPhone" placeholder="Enter Phone" />
                    </div>
                    <div class="form-group" style="display:none;">
                        <label for="txtImage">Picture</label>
                        <input type="file" name="image" value="${(result!=null) ? result.image : ''}" />
                    </div>
                    <div class="form-group">
                        <a href="user?action=list" class="btn btn-default">Cancel</a>
                        <button type="submit" class="btn btn-success">Submit</button>
                    </div>
                </form>
            </div>
        </aside>

        <div class="clearfix"></div>
    </div>
</section>
<script type="text/javascript">
    jQuery(document).ready(function(){
        // validate the form when it is submitted
        jQuery("#userForm").validate();
    });
</script>