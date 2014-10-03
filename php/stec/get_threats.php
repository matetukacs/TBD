<?php
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST');  
	
	require("_file_foo.php");
	require("_mysql_class_wrapper.php");
	
	$res = array
		(
			"code" => 0,
			"comments"	=> "",
			//"comments223"	=> "",
			"error"		=> "No_error",
			"data"	=> ''
		);
		if (!isset($_GET['token']) || !isset($_GET['exchange'])) 
		{
			$res['code'] = 100;
			$res['error'] = "Not enough input param2323";
			//$res['comments223'] = "GET: " + print_r($_GET, true) + " POST: " + print_r($_POST, true);
		}
		else
		{
			$my_mysql_wr = new mysql_class_wrapper();
			// get all stories
			$now_ts = time();
			// $res['data'] = $my_mysql_wr->get_threats("sdfs_token_fsdf", 1, $now_ts);
			$res['data'] = $my_mysql_wr->get_threats($_GET['token'], $_GET['exchange'], $_GET['from']);	
			$res['comments'] = $_GET['from'];	
			$res['timestamp'] = $now_ts;
			if ($res['data']['error'] == 1)
			{
				$res['error'] = 1;
				
			}
		}
		
		echo json_encode(	$res );
	
	
