function getpatientsList()
{
  //  var patientsEndpoint = ' http://www.mocky.io/v2/5a07556d2f00001707e610c7';
var patientsEndpoint = '/api/v1/patients';
    $.ajax({url: patientsEndpoint, success: function(result) {
        var patients = result;
        console.log(patients)
        var template = $("#patient-template").html();
        var i = 1;
        patients = patients.map(function(c)
        {
          var returnObj = c;
          returnObj.patientEmail = returnObj.email;
          returnObj.patientName = returnObj.name;
          returnObj.patientPhone=returnObj.phone;
          returnObj.patientSex=returnObj.sex;
          returnObj.editButton = i;
          i = i+1
          return returnObj;
        })
        var patientsHTML = Mustache.render(template, { patients: patients });
        $("#patients-list").html(patientsHTML);
    }})
}

$(document).ready(function() {
    $("#patient-template-container").load("patient-template.html")
    getpatientsList();
});
