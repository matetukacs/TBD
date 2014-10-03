<?php
	session_start();
	header('Access-Control-Allow-Origin: http://localhost');
	header('Access-Control-Allow-Methods: GET, POST');  
	header('Access-Control-Allow-Credentials: true');
	
	if (empty($_SESSION["is_logined"] ))
	{
		$res['error'] = "Not authorized";
		echo json_encode($res);
		die;
	}
	
	require("_file_foo.php");
	require("_mysql_class_wrapper.php");
	
	$res = array
		(
			"code" => 0,
			"comments"	=> "",
			"error"		=> "No_error",
			"data"	=> ''
		);
		if (empty($_POST['name']) || empty($_POST['type']) || empty($_POST['description']))
		{
			$res['code'] = 100;
			$res['error'] = "Not enough input param";
		}
		else
		{
			$my_mysql_wr = new mysql_class_wrapper();
			// get all stories
			// user_id, name, type = {'public', 'private', 'restricted',}, description,
			$res['data'] = $my_mysql_wr->create_exchange($_SESSION["user_id"], $_POST['name'], $_POST['type'], $_POST['description']);
			
		}
		
		echo json_encode(	$res );
	
	
