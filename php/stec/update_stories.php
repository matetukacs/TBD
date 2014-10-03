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
			"error"		=> "No_error"
		);
	if (!isset($_POST['rel_story_id']))
	{
		$res["code"] = 1;
		$res["error"] = "No `rel_story_id` POST specified";
		$res["comments"] = print_r($_POST, true);   
		die (json_encode(	$res ));
	}
	else if (!isset($_POST['rel_shown_id']))
	{
		$res["code"] = 5;
		$res["error"] = "No `rel_shown_id` POST specified";
		$res["comments"] = print_r($_POST, true);   
		die (json_encode(	$res ));
	}
	else if (!isset($_POST['ontime_id']))
	{
		$res["code"] = 5;
		$res["error"] = "No `rel_shown_id` POST specified";
		$res["comments"] = print_r($_POST, true);   
		die (json_encode(	$res ));
	}
	else
	{
		//drop_data_into_file("insert_stories", $_POST['my_data']);
		
		$my_mysql_wr = new mysql_class_wrapper();
		$my_mysql_wr->update_stories_with_rally_id($_POST['ontime_id'], $_POST['rel_story_id'], $_POST['rel_shown_id']);
		
		$my_mysql_wr->do_commit();
		
		
		
		
		
		
		echo json_encode(	$res );
	}