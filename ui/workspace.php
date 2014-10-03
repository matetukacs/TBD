<?php
header('Access-Control-Allow-Origin: http://10.154.244.56/');
header('Access-Control-Allow-Methods: GET, POST');


?>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link href="lib/bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
</head>
<body>

<div class="header">
    <div class="stec-logo">
        <h1>STEC <small>Security Threats Exchange Cloud</small></h1>
    </div>
    <div class="logged-in"><span class="glyphicon glyphicon-user"></span> Logged in as <span id="username"></span><br> <a href="#">Logout</a> </div>
</div>

<br>

<div class="col-lg-12">
    <h3>Personal workspace</h3>
    <table id="exchanges_table" class="table table-hover">

    </table>

    <hr>

    <div class="col-md-4">

        <form id="create_exchange_form" role="form" action="http://10.154.244.56/stec/create_exchange.php" method="POST">

          <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" placeholder="Name" name="name">
          </div>
          <div class="form-group">
            <label for="type">Type</label>
            <select class="form-control" id="type" name="type">
              <option>private</option>
              <option>restricted</option>
              <option>public</option>
            </select>
          </div>
          <div class="form-group">
            <label for="description">Description</label>
            <input type="text" class="form-control" id="description" placeholder="Description" name="description">
          </div>
          <button id="create_exchange_button" type="submit" class="btn btn-primary btn-lg btn-success"><span class="glyphicon glyphicon-plus"></span> Create exchange</button>

        </form>
    <br><br>
    <a href="main">Back to main page</a>
    </div>
</div>







<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="lib/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>

<script src="js/workspace.js"></script>
</body>
</html>