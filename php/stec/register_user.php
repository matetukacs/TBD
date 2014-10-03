<?php
	header('Access-Control-Allow-Origin: http://localhost');
	header('Access-Control-Allow-Methods: GET, POST');  
	header('Access-Control-Allow-Credentials: true');
	
	require("_file_foo.php");
	require("_mysql_class_wrapper.php");
	require("_security.php");
	$res = array
		(
			"code" => 0,
			"comments"	=> "",
			"error"		=> "No_error",
			"data"	=> ''
		);
		if (empty($_POST['email']) || empty($_POST['pass']))
		{
			$res['code'] = 100;
			$res['error'] = "Not enough input param";
		}
		else
		{
			$my_mysql_wr = new mysql_class_wrapper();
			// get all stories
			
			// name, surename, email, pass, phone, about
			$res['data'] = $my_mysql_wr->register_user($_POST['name'], $_POST['surename'], $_POST['email'], _get_hash_from($_POST['pass']), $_POST['phone'], $_POST['about']);
		}
		
		echo json_encode(	$res );
	
	
