<section>
    <div class="container content">
        <div class="login">
            <% if(request.getParameter("msg") != null){ %>
                <p class="bg-danger"><%= request.getParameter("msg").toString()%></p>
            <% } %>
            <form role="form" action="login_process" method="POST">
                <div class="form-group">
                    <label for="txtUname">User Name</label>
                    <input type="text" name="uname" class="form-control" id="txtUname" placeholder="Enter User Name">
                </div>
                <div class="form-group">
                    <label for="txtPass">Password</label>
                    <input type="password" name="password" class="form-control" id="txtPass" placeholder="Password">
                </div>

                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>
</section>