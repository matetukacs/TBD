<?php
header('Access-Control-Allow-Origin: http://10.154.244.56/');
header('Access-Control-Allow-Methods: GET, POST');  


?>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>STEC Login page</title>
    <link href="lib/bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <h1>STEC</h1>
            <br>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li class="active"><a href="#login" role="tab" data-toggle="tab">Log in</a></li>
                <li><a href="#signup" role="tab" data-toggle="tab">Sign up</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div class="tab-pane active" id="login">
                    <br>

                    <form role="form" id="login_form" method="GET" action="http://10.154.244.56/stec/auth_user.php">
                        <div class="form-group">
                            <label for="login_email">Email</label>
                            <input type="text" class="form-control" id="login_email" placeholder="Enter email" name="email">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" placeholder="Enter password" name="pass">
                        </div>
                        <button type="submit" class="btn btn-default" id="login_button">Log in</button>
                    </form>




                </div>
                <div class="tab-pane" id="signup">
                    <br>
                    <form role="form" method="POST" id="signup_form" action="http://10.154.244.56/stec/register_user.php">
                        <div class="form-group">
                            <label for="name">First name</label>
                            <input type="text" class="form-control" id="name" placeholder="First name" name="name">
                        </div>
                        <div class="form-group">
                            <label for="surename">Surname</label>
                            <input type="text" class="form-control" id="surename" placeholder="Surname" name="surename">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" placeholder="Email" name="email">
                        </div>
                        <div class="form-group">
                            <label for="reg_password">Choose your password</label>
                            <input type="password" class="form-control" id="reg_password" placeholder="Enter password" name="pass">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone number</label>
                            <input type="text" class="form-control" id="phone" placeholder="Phone number" name="phone">
                        </div>
                        <div class="form-group">
                            <label for="about">Additional information</label>
                            <input type="text" class="form-control" id="about" placeholder="Additional information" name="about">
                        </div>
                        <button type="submit" id="reg_button" class="btn btn-default">Sign up</button>
                    </form>
                </div>
            </div>
        </div>
    </div>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="lib/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>

<script src="js/login.js"></script>
</body>
</html>