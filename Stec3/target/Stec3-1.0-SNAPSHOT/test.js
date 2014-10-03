function doAjax( in_url,  method,  in_data, descr)
{
	var jqxhr = $.ajax({
		url: in_url,
		type: method,
  		data:in_data,
  		indexValue: descr
		 })
	  .done(function(data) {
		console.log( "Responce at " +  descr + ":");
		console.log(data);

	  })
	  .fail(function() {
		console.log( "error with " +  descr);
	  })
	  .always(function() {
		console.log( "completed " + descr);
	  });
}


//==========================================
var r = confirm("You are going to run the test. Are you sure");
if (r == true) {
   	// txt = "You pressed OK!";
   	alert("Running AJAX calls");
   	
   	// do to google
   	doAjax("http://localhost/", "GET", null, "Testing google");
   	
} else {
   // txt = "You pressed Cancel!";
}