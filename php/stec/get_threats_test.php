<?php
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST');  
	
	require("_file_foo.php");
	require("_mysql_class_wrapper.php");
	
	$res = array
		(
			"code" => 0,
			"comments"	=> "",
			"error"		=> "No_error",
			"data"	=> ''
		);
		if (false)
		{
			$res['code'] = 100;
			$res['error'] = "Not enough input param";
		}
		else
		{
			$my_mysql_wr = new mysql_class_wrapper();
			// get all stories
			
			$res['data'] = $my_mysql_wr->get_threats("weiuyrwerywiuery", 1, null);
			if ($res['data']['error'] == 1)
				echo "No premission";
			$res['timestamp'] = $now_ts;
			echo json_encode(	$res );
			echo '<hr/>';
			
			$now_ts = time();
			$res['data'] = $my_mysql_wr->get_threats("weiuyrwerywiuery", 1, $now_ts);
			$res['timestamp'] = $now_ts;
			echo json_encode(	$res );
			echo '<hr/>';
			
			$now_ts = time();
			$res['data'] = $my_mysql_wr->get_threats("weiuyrwerywiuery", 1, 1412124029);
			$res['timestamp'] = $now_ts;
			
		}
		
		echo json_encode(	$res );
	
	
