<?php
	require("_mysql.php");
	
	/*
	$mysql_server = "localhost";
	$mysql_user = "alex_3343";
	$mysql_pass = "__some_pass__";
	$mysql_database = "agile";
	*/

/* Tigran


*/
class mysql_class_wrapper
{
	private $con;
	private $error;
	private $comments;
	private $code;
	
	function construct_erorr($code, $error)
	{
		if (isset($code))
			$this->code = $code;
		if (isset($error))
			$this->error = $error;
		
			
		$res = array
		(
			"code" => $this->code,
			"comments"	=> $this->comments,
			"error"		=> $this->error
		);
		return json_encode($res);
	}	
		
	function __construct ()
	{
		require("_mysql.php");
		$this->con = mysqli_connect($mysql_server,$mysql_user,$mysql_pass,$mysql_database) 
					or die($this->construct_erorr(1, mysqli_error($this->con))); 
		
	}
	
	function generateRandomString($length = 10) {
   		 $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
   		 $randomString = '';
   		 for ($i = 0; $i < $length; $i++) {
   		     $randomString .= $characters[rand(0, strlen($characters) - 1)];
  	  }
  	  return $randomString;
	}


	//--------------------------------------------------------------------------------------------------------------------------------
	
	
	
	// function extract all stories, thus ontime grabber was able to query each of them for detailed description
	function get_threats($in_token, $in_exchange, $in_since)
	{		
	
		$ret = $this->_check_for_permission($in_token, $in_exchange, "subscription");
		if ($ret == false)
		{
			$ret = $this->_check_for_permission($in_token, $in_exchange, "publish");
			if ($ret == false)
			{		
				$ret = $this->_check_for_permission($in_token, $in_exchange, "owner");
				if ($ret == false)
				{
					//no auth
					$res['error'] = 1; // no auth
					return $res;
				}
			}
		}
	
		
		//'owner', 'publish', 'subscription', 'none'
		
		$res = array();
		$query = "";
		$long_q = false;
		if (isset($in_since) && $in_since !== null && !empty($in_since))
		{
			$query = "SELECT `id`, `ip`, `description`, `type`, `level` FROM `threat` WHERE `exchange_id` = ? AND `createdat` >= ? ";
			$long_q = true;
			// TODO add auth token info
		}
		else
		{
			$query = "SELECT `id`, `ip`, `description`, `type`, `level` FROM `threat` WHERE `exchange_id` = ?";
		}
		if ($stmt = $this->con->prepare($query)) 
		{
		  if ($long_q)
		  {
		  	 //echo date("Y-m-d H:i:s", $in_since);
		  	 //echo "sfsdfsdf";
		  	 $stmt->bind_param("is", $in_exchange, date("Y-m-d H:i:s", $in_since));
		  }
		  else
		  {
		  	 //echo "ssss";
		  	 $stmt->bind_param("i", $in_exchange);
		  }
		 
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `get_threats`.";
				die($this->construct_erorr(12, mysqli_error($this->con)));
  		  }
  		  if(!$stmt->bind_result($id,$ip,$descr, $type, $level))
  		  {
  		  		$this->comments = "Problem with `get_all_stories_for_ontime`.";
				die($this->construct_erorr(13, mysqli_error($this->con)));
  		  }
  		  
  		 while ($stmt->fetch())
  		  {
  		  		$temp = array(
  		  			'id'				=> $id,
  		  			'ip'				=> $ip,
  		  			'descr'				=> $descr,
  		  			'type'				=> $type,
  		  			'level'				=> $level
  		  		);
  		  		array_push($res, $temp);
  		  }
			
  		  /* close statement */
  		  $stmt->close();
		}	
		else
		{
			$this->comments = "Problem with `get_threats`.";
			die($this->construct_erorr(14, mysqli_error($this->con)));
		}	
		return $res;
	}	 
	
	
	// insert_threats("sdfs_token_fsdf", 1, "1.2.3.4", "this is deme", "dos", 3);
	function insert_threats($in_token, $in_exchange, $in_ip, $in_descr, $in_type, $in_sever, $in_createdbyip)
	{	
		// TODO remove it
		//$in_ip = "0.2.2.0";
		
		$ret = $this->_check_for_permission($in_token, $in_exchange, "publish");
		if ($ret == false)
		{
			$ret = $this->_check_for_permission($in_token, $in_exchange, "owner");
			if ($ret == false)
			{		
				$res['error'] = 1; // no auth
				return $res;
			}
		}
		$token_id = $this->_get_token_id($in_token);
		
		
		$res = array();
		if ($stmt = $this->con->prepare("INSERT INTO `threat`(`exchange_id`, `pushed_by_token`, `ip`, `description`, `type`, `level`, `createdat`, `createdbyip`) ".
										" VALUES (?, ?, ?, ?, ?, ?, NOW(), ?)")) 
		{
		  $stmt->bind_param("iisssss", $in_exchange, $token_id, $in_ip, $in_descr, $in_type, $in_sever, $in_createdbyip);
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `insert_threats`.";
				die($this->construct_erorr(133, mysqli_error($this->con)));
  		  } 		  

			/*
  		  if(!$stmt->bind_result($id))
  		  {
  		  		$this->comments = "Problem with `insert_threats`.";
				die($this->construct_erorr(134, mysqli_error($this->con)));
  		  }
  		 */
  		  
  		  /* close statement */
  		  $stmt->close();
		}	
		else
		{
			$this->comments = "Problem with `insert_threats`.";
			die($this->construct_erorr(135, mysqli_error($this->con)));
		}	
		return $res;
	}	
	
	
	
	
	function get_all_public_exchanges()
	{		
	
		
		//'owner', 'publish', 'subscription', 'none'
		
		$res = array();
		$query = "SELECT `id`, `name`, `type`, `createdat`, `createdby`, `description` FROM `exchange`"
				." WHERE `type` = \"public\" OR `type` = \"name\"";
		if ($stmt = $this->con->prepare($query)) 
		{
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `get_all_public_exchanges`.";
				die($this->construct_erorr(12, mysqli_error($this->con)));
  		  }
  		  if(!$stmt->bind_result($id,$name,$type, $createdat, $createdby, $description))
  		  {
  		  		$this->comments = "Problem with `get_all_public_exchanges`.";
				die($this->construct_erorr(13, mysqli_error($this->con)));
  		  }
  		  
  		 while ($stmt->fetch())
  		  {
  		  		$temp = array(
  		  			'id'				=> $id,
  		  			'name'				=> $name,
  		  			'type'				=> $type,
  		  			'createdat'			=> $createdat,
  		  			'createdby'			=> $createdby,
  		  			'description'		=> $description
  		  		);
  		  		array_push($res, $temp);
  		  }
			
  		  /* close statement */
  		  $stmt->close();
		}	
		else
		{
			$this->comments = "Problem with `get_all_public_exchanges`.";
			die($this->construct_erorr(14, mysqli_error($this->con)));
		}	
		return $res;
	}	
	
	
	
	function get_all_distinct_threats()
	{		
	
		
		//'owner', 'publish', 'subscription', 'none'
		
		$res = array();
		$query = "SELECT DISTINCT `ip` FROM `threat` ";
		if ($stmt = $this->con->prepare($query)) 
		{
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `get_all_distinct_threats`.";
				die($this->construct_erorr(12, mysqli_error($this->con)));
  		  }
  		  if(!$stmt->bind_result($ip))
  		  {
  		  		$this->comments = "Problem with `get_all_distinct_threats`.";
				die($this->construct_erorr(13, mysqli_error($this->con)));
  		  }
  		  
  		 while ($stmt->fetch())
  		  {
  		  		$temp = array(
  		  			'ip'				=> $id
  		  		);
  		  		array_push($res, $temp);
  		  }
			
  		  /* close statement */
  		  $stmt->close();
		}	
		else
		{
			$this->comments = "Problem with `get_all_distinct_threats`.";
			die($this->construct_erorr(14, mysqli_error($this->con)));
		}	
		return $res;
	}	
	
	function get_all_my_exchanges($in_id)
	{		
	
		
		//'owner', 'publish', 'subscription', 'none'
		
		$res = array();
		$query = "SELECT `exchange`.`id`, `exchange`.`name`, ".
						 "`exchange`.`type`, `exchange`.`createdat`, ".
						 " `exchange`.`createdby`, `exchange`.`description`, ".
						 " `user_to_exchange`.`own_type` FROM `exchange` ".
				 " INNER JOIN `user_to_exchange` ON `user_to_exchange`.`exchange_id` = `exchange`.`id` ".
				 " WHERE `user_to_exchange`.`user_id` = ?";
		//echo $query;
		if ($stmt = $this->con->prepare($query)) 
		{
		   $stmt->bind_param("i", $in_id);
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `get_all_my_exchanges`.";
				die($this->construct_erorr(12, mysqli_error($this->con)));
  		  }
  		  if(!$stmt->bind_result($id,$name,$type, $createdat, $createdby, $description, $ownship))
  		  {
  		  		$this->comments = "Problem with `get_all_my_exchanges`.";
				die($this->construct_erorr(13, mysqli_error($this->con)));
  		  }
  		  
  		 while ($stmt->fetch())
  		  {
  		  		$temp = array(
  		  			'id'				=> $id,
  		  			'name'				=> $name,
  		  			'type'				=> $type,
  		  			'createdat'			=> $createdat,
  		  			'createdby'			=> $createdby,
  		  			'description'		=> $description,
  		  			'ownship'			=> $ownship
  		  		);
  		  		array_push($res, $temp);
  		  }
			
  		  /* close statement */
  		  $stmt->close();
		}	
		else
		{
			$this->comments = "Problem with `get_all_my_exchanges`.";
			die($this->construct_erorr(14, mysqli_error($this->con)));
		}	
		return $res;
	}	
	
	// get_my_info (in - id);
	function get_my_info($in_id)
	{		
	
		
		//'owner', 'publish', 'subscription', 'none'
		
		$res = array();
		$query = "SELECT `id`, `name`, `surename`, `email`, `phone`, `about`, `createdat`, `company`, `company_address`".
				" FROM `user` WHERE `id` = ?";
		//echo $query;
		if ($stmt = $this->con->prepare($query)) 
		{
		   $stmt->bind_param("i", $in_id);
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `get_my_info`.";
				die($this->construct_erorr(12, mysqli_error($this->con)));
  		  }
  		  if(!$stmt->bind_result($id,$name,$surename, $email, $phone, $about, $createdat, $company, $company_address))
  		  {
  		  		$this->comments = "Problem with `get_my_info`.";
				die($this->construct_erorr(13, mysqli_error($this->con)));
  		  }
  		  
  		 while ($stmt->fetch())
  		  {
  		  		$temp = array(
  		  			'id'				=> $id,
  		  			'name'				=> $name,
  		  			'surename'			=> $surename,
  		  			'email'				=> $email,
  		  			'phone'				=> $phone,
  		  			'about'				=> $about,
  		  			'createdat'			=> $createdat,
  		  			'company'			=> $company,
  		  			'company_address'	=> $company_address
  		  		);
  		  		array_push($res, $temp);
  		  }
			
  		  /* close statement */
  		  $stmt->close();
		}	
		else
		{
			$this->comments = "Problem with `get_my_info`.";
			die($this->construct_erorr(14, mysqli_error($this->con)));
		}	
		return $res;
	}	
	//
	// // id of the useer, id of the exchange
	//		$res['data'] = $my_mysql_wr->delete_exchange(1, 4);
	function delete_exchange($in_user_id, $in_exchange)
	{		
	
		
		//'owner', 'publish', 'subscription', 'none'
		
		$res = array();
		$query = "DELETE FROM `user_to_exchange` WHERE `exchange_id` = ? AND `user_id` = ?";
		//echo $query;
		if ($stmt = $this->con->prepare($query)) 
		{
		   $stmt->bind_param("ii", $in_exchange, $in_user_id);
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `delete_exchange`.";
				die($this->construct_erorr(12, mysqli_error($this->con)));
  		  }
  		  /* close statement */
  		  $stmt->close();
		}	
		else
		{
			$this->comments = "Problem with `delete_exchange`.";
			die($this->construct_erorr(14, mysqli_error($this->con)));
		}	
		
		$query = "DELETE FROM `exchange` WHERE `id` = ? AND `createdby` = ?";
		//echo $query;
		if ($stmt = $this->con->prepare($query)) 
		{
		   $stmt->bind_param("ii", $in_exchange, $in_user_id);
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `delete_exchange`.";
				die($this->construct_erorr(18, mysqli_error($this->con)));
  		  }
  		  /* close statement */
  		  $stmt->close();
		}	
		else
		{
			$this->comments = "Problem with `delete_exchange`.";
			die($this->construct_erorr(19, mysqli_error($this->con)));
		}	
		return $res;
	}	

	// create_exchange(1, "This is new exchange", "public", "Some description");
	function create_exchange($in_user_id, $in_name, $in_type, $in_descr)
	{	
		//$this->con->autocommit(FALSE);
		
		$res = array();
		if ($stmt = $this->con->prepare("INSERT INTO `exchange` (`name`, `type`, `createdat`, `createdby`, `description`)   ".
										" VALUES (?, ?, NOW(), ?, ?)")) 
		{
		  $stmt->bind_param("ssis", $in_name, $in_type, $in_user_id, $in_descr);
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `create_exchange`.";
				die($this->construct_erorr(133, mysqli_error($this->con)));
  		  } 		  
			/*
  		  if(!$stmt->bind_result($id))
  		  {
  		  		$this->comments = "Problem with `insert_threats`.";
				die($this->construct_erorr(134, mysqli_error($this->con)));
  		  }
  		 */
  		 
  		  $res['new_exchange_id'] = $this->con->insert_id;
		  $res['comments'] .= "new exchange id = " + $this->con->insert_id;
		  
  		  /* close statement */
  		  $stmt->close();
		}	
		else
		{
			$this->comments = "Problem with `create_exchange`.";
			die($this->construct_erorr(135, mysqli_error($this->con)));
		}	
		
		// now - do binding

		if ($stmt = $this->con->prepare("INSERT INTO `user_to_exchange`(`user_id`, `exchange_id`, `own_type`, `updatedat`)  ".
										" VALUES (?, ?, \"owner\", NOW())")) 
		{
		  $stmt->bind_param("ii", $in_user_id, $res['new_exchange_id']);
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `create_exchange`.";
				die($this->construct_erorr(173, mysqli_error($this->con)));
  		  } 		  
			/*
  		  if(!$stmt->bind_result($id))
  		  {
  		  		$this->comments = "Problem with `insert_threats`.";
				die($this->construct_erorr(134, mysqli_error($this->con)));
  		  }
  		 */
  		  
  		  /* close statement */
  		  $stmt->close();
		}	
		else
		{
			$this->comments = "Problem with `create_exchange`.";
			die($this->construct_erorr(175, mysqli_error($this->con)));
		}
		
		return $res;
	}
	
	
	
	// register_user("name", "surename", "email", _get_hash_from("mypass"), "+2323_phone", "This  about myself")
	function register_user($in_name, $in_surename, $in_email, $in_hash, $in_phone, $in_info)
	{	
		$res = array();
		if ($stmt = $this->con->prepare("INSERT INTO `user`(`name`, `surename`, `email`, `pass`, `phone`, `about`, `createdat`, `company`, `company_address`)   ".
										" VALUES (?, ?, ?, ?, ?, ?, NOW(), \"\", \"\")")) 
		{
		  $stmt->bind_param("ssssss", $in_name, $in_surename, $in_email, $in_hash, $in_phone, $in_info);
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `register_user`.";
				die($this->construct_erorr(133, mysqli_error($this->con)));
  		  } 		  

			/*
  		  if(!$stmt->bind_result($id))
  		  {
  		  		$this->comments = "Problem with `insert_threats`.";
				die($this->construct_erorr(134, mysqli_error($this->con)));
  		  }
  		 */
  		  
  		  /* close statement */
  		  $stmt->close();
		}	
		else
		{
			$this->comments = "Problem with `register_user`.";
			die($this->construct_erorr(135, mysqli_error($this->con)));
		}	
		return $res;
	}	
	
	//
	function auth_user($in_email, $in_pass)
	{	
		//echo $in_token;
		//echo $in_exchange;
		//echo $in_exchange;
		$res = array();
		if ($stmt = $this->con->prepare("SELECT `id`, `email` FROM `user`".
										" WHERE `email` = ? AND `pass` = ?")) 
		{
		  $stmt->bind_param("ss", $in_email, $in_pass);
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `auth_user`.";
				die($this->construct_erorr(153, mysqli_error($this->con)));
  		  } 		  

  		  if(!$stmt->bind_result($id, $email))
  		  {
  		  		$this->comments = "Problem with `auth_user`.";
				die($this->construct_erorr(154, mysqli_error($this->con)));
  		  }
  		 
  		  
  		  $ret_email = "";
  		  while ($stmt->fetch())
  		  { 
  		  	$ret_email = $email;
  		  	$ret_id = $id;  
  		  }
  		  //echo 	$ret_email;
  		  //echo  $in_email;
  		  //echo ":----";
  		  if ($ret_email == $in_email)
  		  	$res['user_id'] = $ret_id;
  		  else
  		  	$res['user_id'] = false;
  		  	
  		  $stmt->close();
  		  return $res;
		}	
		else
		{
			$this->comments = "Problem with `auth_user`.";
			die($this->construct_erorr(155, mysqli_error($this->con)));
		}	
		return $res;
	}	 
	
	
	// ============
	// in_right = set('owner', 'publish', 'subscription', 'none')
	function _check_for_permission($in_token, $in_exchange, $in_right)
	{	
		//echo $in_token;
		//echo $in_exchange;
		//echo $in_exchange;
		$res = array();
		if ($stmt = $this->con->prepare("SELECT `exchange_id` FROM `user_to_exchange`".
									" INNER JOIN `tokens_to_connections` ON `tokens_to_connections`.`connection_id` = `user_to_exchange`.`id` ".
									" INNER JOIN `tokens` ON `tokens_to_connections`.`token_id` = `tokens`.`id`".
									" WHERE `tokens`.`token_value` = ? AND `exchange_id` = ? AND `own_type` = ?")) 
		{
		  $stmt->bind_param("sis", $in_token, $in_exchange, $in_right);
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `_check_for_permission`.";
				die($this->construct_erorr(153, mysqli_error($this->con)));
  		  } 		  

  		  if(!$stmt->bind_result($id))
  		  {
  		  		$this->comments = "Problem with `_check_for_permission`.";
				die($this->construct_erorr(154, mysqli_error($this->con)));
  		  }
  		 
  		  
  		  $ret_id = 0;
  		  while ($stmt->fetch())
  		  { 
  		  	$ret_id = $id;  
  		  }
  		  //echo "----";
  		  //echo $ret_id;
  		  
  		  /* close statement */
  		  $stmt->close();
  		  if ($ret_id == $in_exchange)
  		  	return true;
  		  else
  		  	return false;
		}	
		else
		{
			$this->comments = "Problem with `_check_for_permission`.";
			die($this->construct_erorr(155, mysqli_error($this->con)));
		}	
		return $res;
	}	
	
	function _get_token_id($in_token)
	{	
		//echo $in_token;
		//echo $in_exchange;
		//echo $in_exchange;
		$res = array();
		if ($stmt = $this->con->prepare("SELECT `id` FROM `tokens` WHERE `token_value` = ?")) 
		{
		  $stmt->bind_param("s", $in_token);
  		  /* execute query */
  		  if(!$stmt->execute())
  		  {
  		  		$this->comments = "Problem with `_get_token_id`.";
				die($this->construct_erorr(153, mysqli_error($this->con)));
  		  } 		  

  		  if(!$stmt->bind_result($id))
  		  {
  		  		$this->comments = "Problem with `_get_token_id`.";
				die($this->construct_erorr(154, mysqli_error($this->con)));
  		  }
  		 
  		  
  		  $ret_id = 0;
  		  while ($stmt->fetch())
  		  { 
  		  	$ret_id = $id;  
  		  }
  		  //echo "----";
  		  //echo $ret_id;
  		  
  		  /* close statement */
  		  $stmt->close();
  		  return $ret_id;
		}	
		else
		{
			$this->comments = "Problem with `_get_token_id`.";
			die($this->construct_erorr(155, mysqli_error($this->con)));
		}	
		return $res;
	}	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	function do_commit()
	{
		$this->con->commit();
	}
	function do_rollback()
	{
		$this->con->rollback();
	}
	
}
	
	
	
	