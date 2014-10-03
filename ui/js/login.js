var ip = "10.154.244.56";

$(function() {

    $("#reg_button").click(function() {
        $("#signup_form").submit(function(e) {
            e.preventDefault();

            $.ajax({
                url: "http://" + ip + "/stec/register_user.php",
                type: 'post',
                dataType: 'json',
                data: $("#signup_form").serialize(),
                success: function(data) {
                    console.log("ff");
                }
            });
        });
    });

    $("#login_button").click(function() {
        $("#login_form").submit(function(e) {
            e.preventDefault();

            $.ajax({
                url: "http://"+ ip +"/stec/auth_user.php",
                type: 'get',
                crossDomain: true,
                xhrFields: {
                    withCredentials: true
                },
                //dataType: 'json',
                data: $("#login_form").serialize(),
                success: function(data) {
                    document.location = "main";
                }
            });
        });

        //document.location = "http://www.google.com/";
    });
});