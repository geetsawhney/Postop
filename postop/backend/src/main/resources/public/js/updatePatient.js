function updatePatient() {
    console.log("here!")
    //$("#update-patient-btn").click(function() {
      console.log("inside update patient")
        var data = {
            name:$("#up-name").val(),
            email:$("#up-email").val(),
            ssn:"",
            id:"",
            sex:$("#up-sex").val(),
            dob:$("#up-date").val(),
            address:$("#up-address").val(),
            phone:$("#up-phone").val(),
            hospitalVisitReason:$("#up-hospital-visit-reason").val(),
            utiVisitCount:5,
           catheterUsage:false,
           diabetic:false,
            lastVisitDate:"2017-10-12"
          }
        console.log(data)
        var target=$("#up-email").val()
        console.log(target)
        var patientURL='/api/v1/patient/'+target
        var postRequest = $.ajax({
            type: 'PUT',
            url: patientURL,
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(data)
            {
              location.reload();              
            }
        });
    //})
}

$(document).ready(function() {
    console.log("here....")
    //updatePatient()
});
