<?php
	header('Access-Control-Allow-Origin: http://localhost');
	header('Access-Control-Allow-Methods: GET, POST');  
	header('Access-Control-Allow-Credentials: true');
	
	
	require("_file_foo.php");
	require("_mysql_class_wrapper.php");
	
	$res = array
		(
			"code" => 0,
			"comments"	=> "",
			"error"		=> "No_error",
			"data"	=> ''
		);
		if (!isset($_POST['token']) || !isset($_POST['exchange']) || !isset($_POST['ip']) || !isset($_POST['descr']) || !isset($_POST['type']) || !isset($_POST['level']))
		{
			$res['code'] = 100;
			$res['error'] = "Not enough input param";
		}
		else
		{
			$my_mysql_wr = new mysql_class_wrapper();
			// get all stories
			if (!empty($_SERVER['HTTP_CLIENT_IP'])) {
				$ip = $_SERVER['HTTP_CLIENT_IP'];
			} elseif (!empty($_SERVER['HTTP_X_FORWARDED_FOR'])) {
				$ip = $_SERVER['HTTP_X_FORWARDED_FOR'];
			} else {
				$ip = $_SERVER['REMOTE_ADDR'];
			}
			// token,  exchange_id,  ip, descr, type, level
			$res['data'] = $my_mysql_wr->insert_threats($_POST['token'], $_POST['exchange'], $_POST['ip'], $_POST['descr'], $_POST['type'], $_POST['level'], $ip);
			
		}
		
		echo json_encode(	$res );
	
	
