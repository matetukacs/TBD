<?php
	session_start();
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
		if (empty($_GET['email']) || empty($_GET['pass']))
		{
			$res['code'] = 100;
			$res['error'] = "Not enough input param";
		}
		else
		{
			$my_mysql_wr = new mysql_class_wrapper();
			// get all stories
			
			// name, surename, email, pass, phone, about
			$res['data'] = $my_mysql_wr->auth_user($_GET['email'], _get_hash_from($_GET['pass']));
			//echo $_GET['email'];
			//echo " ";
			//echo _get_hash_from($_GET['pass']);
			if (!empty($res['data']['user_id']))
			{	
				//register session
				$_SESSION["is_logined"] = true;
				$_SESSION["user_id"] = $res['data']['user_id'];
			}
			else
			{
				$res['code'] = 30;
				$res['error'] = "Auth failed";
			}
		}
		
		echo json_encode(	$res );
	
	
