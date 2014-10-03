<?php
	function generateRandomString($length = 10) {
   		 $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
   		 $randomString = '';
   		 for ($i = 0; $i < $length; $i++) {
   		     $randomString .= $characters[rand(0, strlen($characters) - 1)];
  	  }
  	  return $randomString;
	}

	function drop_data_into_file($source, $in_json_arr)
	{
		// TODO
		$new_auto_gen_file_name = generateRandomString(12);
		
		date_default_timezone_set('America/Los_Angeles');//or change to whatever timezone you want
		$date = date('m/d/Y h:i:s a', time());
		file_put_contents("_data/__data_log.txt", "New Ajax recived from ".$source.". Time [".$date."]. File = " . $new_auto_gen_file_name . "\n", FILE_APPEND);		
		
		// write to file
		$myfile = fopen("_data/".$new_auto_gen_file_name.".txt", "w");
		fwrite($myfile, print_r($in_json_arr, true));
		fclose($myfile);
	}