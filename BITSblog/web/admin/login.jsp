<%-- 
    Document   : login
    Created on : Aug 3, 2013, 5:13:04 PM
    Author     : RBroyles
--%>

<?xml version='1.0' encoding='UTF-8' ?> 
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.bitsblog.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.forms.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/blog.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js" />
        <script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
        <title>${blogBacker.blog.name} :: <ui:insert name="PageTitle">Page Title</ui:insert> </title>

    </head>

    <body>

        <!-- Part 1: Wrap all page content here -->
        <div id="wrap">
            <!-- Begin page content -->
            <div class="container">
                <div class="row">
                    <div class="span12">
                        <div class="page-header">
                            <h1>BITSblog Admin</h1>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="span4 offset3">
                        <form action="j_security_check" method="post" class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label" for="username">username</label>
                                <div class="controls">
                                    <input type="text" size="20" id="username" name="j_username" />
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="pwd">password</label>
                                <div class="controls">
                                    <input type="password" size="20" id="pwd" name="j_password" />
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <input type="submit" class="btn btn-primary" value="log in" />
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div id="push"></div>
        </div>
        <div id="footer">
            <div class="container">
                <center>
                    <p class="muted credit">
                        (c) 2013 Ryan Broyles. Powered by BITSblog.
                    </p>
                </center>
            </div>
        </div>
    </body>

</html>
