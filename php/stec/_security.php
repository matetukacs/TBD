<?php
	$var_salt = "kjfgkdhgfjh28939yf9fhsdkjfhkjsdf";
	$options = [
		'cost' => 11,
	];
	
	function _get_hash_from($in_str)
	{
		return hash("sha512",$in_str.$var_salt);
	}