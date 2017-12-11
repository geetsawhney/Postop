function EditPatient() {
    $(".editPatient").click(function () {
        //  var patientsEndpoint = ' http://www.mocky.io/v2/5a07556d2f00001707e610c7';

        var target = $(this).val();
        //console.log("Target ="+target);
        var patientsEndpoint = '/api/v1/patient/' + target;
        $.ajax({
            url: patientsEndpoint, success: function (result) {
                var patients = result;
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
                var gender_option_2;
                if(patients.sex=="M")
                {
                  gender_option_2="F";
                }
                else {
                  gender_option_2="M";
                }

                var checkfor1;
                if(patients.diabetic==true)
                {
                  document.getElementById("up-is-diabetic").checked = true;
                  console.log("is diabetic");
                }
                else {

                }

                var catheterUsage;
                if(patients.catheterUsage==true)
                {
                  document.getElementById("up-uses-catheter").checked = true;
                  console.log("is catheter user");
                }
                else {

                }
                var data = {
                    patientEmail: patients.email,
                    patientName: patients.name,
                    patientPhone: patients.phone,
                    patientSex: patients.sex,
                    patientDOB: patients.dob,
                    patientAddress: patients.address,
                    patientReason: patients.hospitalVisitReason,
                    lastVisitDate: patients.lastVisitDate,
                    UTIcount: patients.utiVisitCount,
                    deviceId: patients.deviceId,
                    gender_option_2: gender_option_2

                };
                console.log("Email = " + patients.email);
                // patients.patientEmail = patients.email;
                //   patients.patientName = patients.name;
                //   patients.patientPhone=patients.phone;
                //   patients.patientSex=patients.sex;
                var patientsHTML = Mustache.render(template, data);
                console.log(patientsHTML)
                $("#patient-edit-template-container").html(patientsHTML);
                $("#patient-edit-template-container").css('display', 'block');
                $("#patient-list-container").css('display', 'none');
            }
        })
    })
}

$(document).ready(function () {
    $("#patient-edit-template-container").load("patient-edit-template.html")
    EditPatient();
});
