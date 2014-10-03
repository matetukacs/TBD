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
        <div class="logged-in"><span class="glyphicon glyphicon-user"></span> Logged in as <span id="username">Dinasour</span><br> <a href="#">Logout</a> </div>
    </div>

    <br>
    <div class="buttons">
        <button type="button" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-download-alt"></span> Download client app</button>
        <br><br>
        <button onclick="document.location='workspace'" type="button" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon glyphicon-circle-arrow-right"></span> Personal workspace</button>
    </div>

    <div class="col-lg-8">
        <table id="exchanges_table" class="table table-hover">
            <tr>
                <td>Text</td>
                <td class="subscribe"><a href="">Subscribe</a> </td>
            </tr>
            <tr>
                <td>Text</td>
                <td class="subscribe"><a href="">Subscribe</a> </td>
            </tr>
            <tr>
                <td>Text</td>
                <td class="subscribe"><a href="">Subscribe</a> </td>
            </tr>
            <tr>
                <td>Text</td>
                <td class="subscribe"><a href="">Subscribe</a> </td>
            </tr>
            <tr>
                <td>Text</td>
                <td class="subscribe"><a href="">Subscribe</a> </td>
            </tr>
            <tr>
                <td>Text</td>
                <td class="subscribe"><a href="">Subscribe</a> </td>
            </tr>
            <tr>
                <td>Text</td>
                <td class="subscribe"><a href="">Subscribe</a> </td>
            </tr>
        </table>
    </div>





<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="lib/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>

<script src="js/main.js"></script>
</body>
</html>