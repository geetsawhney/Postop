function EditPatient() {
    $(".editPatient").click(function() {
      //  var patientsEndpoint = ' http://www.mocky.io/v2/5a07556d2f00001707e610c7';

    var target=$(this).val();
    //console.log("Target ="+target);
    var patientsEndpoint = '/api/v1/patient/'+target;
        $.ajax({url: patientsEndpoint, success: function(result) {
            var patients= result;
            console.log(patients)

            var template = $("#patient-edit-template").html();
            // patients = patients.map(function(c)
            // {
            //   var returnObj = c;
            //   returnObj.patientEmail = returnObj.email;
            //   returnObj.patientName = returnObj.name;
            //   returnObj.patientPhone=returnObj.phone;
            //   returnObj.patientSex=returnObj.sex;
            //   return returnObj;
            // })

            var data = {
              patientEmail: patients.email,
              patientName: patients.name,
              patientPhone: patients.phone,
              patientSex: patients.sex
            };
            console.log("Email = "+patients.email);
            // patients.patientEmail = patients.email;
            //   patients.patientName = patients.name;
            //   patients.patientPhone=patients.phone;
            //   patients.patientSex=patients.sex;
            var patientsHTML = Mustache.render(template, data);
            console.log(patientsHTML)
            $("#patient-edit-template-container").html(patientsHTML);
            $("#patient-edit-template-container").css('display', 'block');
            $("#patient-list-container").css('display', 'none');
        }})
    })
  }
    $(document).ready(function() {
        $("#patient-edit-template-container").load("patient-edit-template.html")
        EditPatient();
    });
