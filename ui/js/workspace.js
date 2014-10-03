var ip = "10.154.244.56";

$(function() {

    var my_username;



    $.ajax({
        url: "http://" + ip + "/stec/get_my_info.php",
        type: "get",
        dataType: "json",
        crossDomain: true,
        xhrFields: {
            withCredentials: true
        },
        success: function(data) {

            //console.log(data);
            my_username = data.data[0].name;

            $("#username").html(my_username);
        }
    });

            $.ajax({
                url: "http://" + ip + "/stec/get_my_exchanges.php",
                type: 'get',
                dataType: 'json',
                crossDomain: true,
                xhrFields: {
                    withCredentials: true
                },
                success: function(data) {
                    console.log(data);
                    var tableOutput="";
                    for (var key=0, size=data.data.length; key<size; key++) {
                        tableOutput+= "<tr class=\"row\" data-id=\""+ data.data[key].id +"\"> \
                            <td>" + data.data[key].createdat + "</td> \
                            <td>" + data.data[key].name + "</td> \
                            <td>" + data.data[key].description + "</td> \
                            <td>" + data.data[key].ownship + "</td> \
                            <td>" + data.data[key].type + "</td> \
                            <td class=\"subscribe\"><a href=\"#\"><span class=\"delete\">Delete</span></a></td> \
                        </tr>";

                    }

                    $("#exchanges_table").html(tableOutput);

                    $(".delete").click(function(e) {
                        e.stopPropagation();

                        $.ajax({
                            url: "http://" + ip + "/stec/delete_exchange.php",
                            type: "post",
                            dataType: 'json',
                            crossDomain: true,
                            xhrFields: {
                                withCredentials: true
                            },
                            data: {
                                "exchange_id": $(this).closest('.row').attr('data-id')
                            }

                        });
                        location.reload(true);

                    });

                }
            });

            $("#create_exchange_button").click(function() {
                $("#create_exchange_form").submit(function(e) {
                    e.preventDefault();

                    console.log("im here");

                    $.ajax({
                        url: "http://" + ip + "/stec/create_exchange.php",
                        type: 'post',
                        crossDomain: true,
                        xhrFields: {
                            withCredentials: true
                        },
                        dataType: 'json',
                        data: $("#create_exchange_form").serialize(),
                        success: function(data) {
                            location.reload(true);
                        }
                    });
                });
            });
});