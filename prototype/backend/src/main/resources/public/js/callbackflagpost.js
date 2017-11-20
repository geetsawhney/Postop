function Callbackflag() {
    $("#callback-btn").click(function() {
        var data = {
            email: $("#callback-btn").val(),
            isResolved: true
        }
        console.log(data)

        var postRequest = $.ajax({
            type: 'PUT',
            url: '/api/v1/patient/' + data.email + '/callback',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(data)
            {
              location.reload();
                //alert('Post success')
            }
        });
        // postRequest.error(function() {
        //     alert('Error')
        // })
    })
}

$(document).ready(function() {
    Callbackflag()
});
