function Editnotification() {
    $(".editNotification").click(function () {
        //  var notificationsEndpoint = ' http://www.mocky.io/v2/5a07556d2f00001707e610c7';

        var notificationsEndpoint = '/api/v1/nurse/notification/';
        $.ajax({
            url: notificationsEndpoint, success: function (result) {
                var notifications = result;
                console.log(notifications)

                var template = $("#notification-edit-template").html();
                if(notifications.interval=="7")
                {
                  notificationInterval_2:"14";
                  notificationInterval_3:"21";
                  notificationInterval_4:"28";
                }
                else if(notifications.interval=="14")
                {
                  notificationInterval_2:"7";
                  notificationInterval_3:"21";
                  notificationInterval_4:"28";
                }
                else if(notifications.interval=="21")
                {
                  notificationInterval_2:"7";
                  notificationInterval_3:"14";
                  notificationInterval_4:"28";
                }
                else if(notifications.interval=="28")
                {
                  notificationInterval_2:"7";
                  notificationInterval_3:"14";
                  notificationInterval_4:"21";
                }
                var data = {
                    notificationLabel: notifications.label,
                    notificationStart: notifications.start,
                    notificationEnd: notifications.end,
                    notificationInterval: notifications.interval,
                    notificationInterval_2: notificationInterval_2,
                    notificationInterval_3: notificationInterval_3,
                    notificationInterval_4: notificationInterval_4
                };
                console.log("Criticality = " + notifications.label);
                var notificationsHTML = Mustache.render(template, data);
                console.log(notificationsHTML)
                $("#notification-edit-template-container").html(notificationsHTML);
                $("#notification-edit-template-container").css('display', 'block');
                $("#notification-list-container").css('display', 'none');
            }
        })
    })
}

$(document).ready(function () {
    $("#notification-edit-template-container").load("notification-edit-template.html")
    Editnotification();
});
