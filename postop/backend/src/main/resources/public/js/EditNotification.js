function Editnotification() {
    $(".editNotification").click(function () {
        //  var notificationsEndpoint = ' http://www.mocky.io/v2/5a07556d2f00001707e610c7';
        console.log("i came after the click");
        var target=$(this).val();
        var notificationsEndpoint = '/api/v1/nurse/notification';
        $.ajax({
            url: notificationsEndpoint, success: function (result) {
                var notifications = result;
                console.log(notifications);
                var i=0;
                if(notifications[0].label==target)
                {
                    i=0;
                }
                else if(notifications[1].label==target)
                {
                    i=1;
                }
                else if(notifications[2].label==target)
                {
                    i=2;
                }
                else if(notifications[3].label==target)
                {
                    i=3;
                }

                var notificationInterval_2;
                var notificationInterval_3;
                var notificationInterval_4;
                var template = $("#notification-edit-template").html();
                if(notifications[i].interval=="7")
                {
                  notificationInterval_2="14";
                  notificationInterval_3="21";
                  notificationInterval_4="28";
                }
                else if(notifications[i].interval=="14")
                {
                  notificationInterval_2="7";
                  notificationInterval_3="21";
                  notificationInterval_4="28";
                }
                else if(notifications[i].interval=="21")
                {
                  notificationInterval_2="7";
                  notificationInterval_3="14";
                  notificationInterval_4="28";
                }
                else if(notifications[i].interval=="28")
                {
                  notificationInterval_2="7";
                  notificationInterval_3="14";
                  notificationInterval_4="21";
                }
                var data = {
                    notificationLabel: notifications[i].label,
                    notificationStart: notifications[i].start,
                    notificationEnd: notifications[i].end,
                    notificationInterval: notifications[i].interval,
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
  console.log("i came into the edit button");
    $("#notification-edit-template-container").load("notification-edit-template.html")
    Editnotification();
});
