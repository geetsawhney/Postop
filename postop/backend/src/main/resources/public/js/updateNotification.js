function updatenotification() {
    console.log("here!")
      var checkVal = checkValues();
        var fullAddress = $("#up-address1").val() + "|" + $("#up-address2").val();
      if(checkVal==1)
      {
      console.log("inside update notification")
        var data = {
            label:$("#notification-label").val(),
            start:$("#notification-start").val(),
            end:$("#notification-end").val(),
            interval:$("#notification-interval").val()
          }
        console.log(data)

        var notificationURL='/api/v1/nurse/notification/'
        var postRequest = $.ajax({
            type: 'PUT',
            url: notificationURL,
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(data)
            {
              location.reload();
            }
        });
  }  //})
}

$(document).ready(function() {
    console.log("here....")
  //  updatenotification()
});
