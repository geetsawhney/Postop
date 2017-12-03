/*function Patientflag() {
    $("#edit-btn").click(function() {
        var data = {
            email: $("#edit-btn").val(),
            isResolved: true
        }
        console.log(data)

        var postRequest = $.ajax({
            type: 'GET',
            url: 'http://10.194.107.57:8080/api/v1/patient/' + data.email + '/callback',
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
*/
