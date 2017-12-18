function checkValues()
{
    var startVal=$("#notification-start").val();
    var endVal=$("#notification-end").val();
    console.log(startVal);
    console.log(endVal);
    if(endVal <= startVal)
    {
      return 0;
    }
    else {
      return 1;
    }
}
function updatenotification() {
    console.log("here!");
    var checkVal=checkValues();
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

        var notificationURL='/api/v1/nurse/notification'
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
      }
      else {
              alert("end value cannot be greater than start value");
      }

  }  //})


$(document).ready(function() {
    console.log("here....")
  //  updatenotification()
});
