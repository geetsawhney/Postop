function getcallbacksList() {
  //  var callbacksEndpoint = ' http://www.mocky.io/v2/5a07556d2f00001707e610c7';
var callbacksEndpoint = '/api/v1/patients/callbacks';
    $.ajax({url: callbacksEndpoint, success: function(result) {
        var callbacks = result;
        console.log(callbacks)
        var template = $("#callback-template").html();


        // var callbacksHTML = callbacks.map(function(callback) {
        //     return Mustache.render(template, { severity: callback.Severity, patientName: callback.patientName, patientEmail: callback.patientEmail,
        //     patientPhone: callback.patientPhone});
        // });
        var i = 1;
        callbacks = callbacks.map(function(c) {
          var returnObj = c;
          returnObj.patientEmail = returnObj.email;
          returnObj.stringSeverity = (returnObj.severity>=5) ? 'Critical' : (returnObj.severity>=3 ? 'Medium': 'Low');
          returnObj.callbackBtn = i;
        // returnObj.patientName = returnObj.severity;
          returnObj.displayColor = (returnObj.severity>=5) ? 'red' : (returnObj.severity>=3 ? 'orange': 'green');
          i = i+1;
          return returnObj;
        })
        var callbacksHTML = Mustache.render(template, { callbacks: callbacks });


        $("#callbacks-list").html(callbacksHTML);
    }})
}

$(document).ready(function() {
    $("#callback-template-container").load("callback-template.html")
    getcallbacksList();
});
