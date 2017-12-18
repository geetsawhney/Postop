function getnotificationsList()
{
  //  var notificationsEndpoint = ' http://www.mocky.io/v2/5a07556d2f00001707e610c7';
var notificationsEndpoint = '/api/v1/nurse/notification';
    $.ajax({url: notificationsEndpoint, success: function(result) {
        var notifications = result;
        console.log(notifications)
        var template = $("#notification-template").html();
        var i = 1;
        notifications = notifications.map(function(c)
        {
          var returnObj = c;
          returnObj.notificationLabel = returnObj.label;
          returnObj.notificationStart = returnObj.start;
          returnObj.notificationEnd=returnObj.end;
          returnObj.notificationInterval=returnObj.interval;
          returnObj.editButton = i;
          i = i+1
          return returnObj;
        })
        var notificationsHTML = Mustache.render(template, { notifications: notifications });
        $("#notifications-list").html(notificationsHTML);
    }})
}

$(document).ready(function() {
    $("#notification-template-container").load("notification-template.html")
    getnotificationsList();
});
