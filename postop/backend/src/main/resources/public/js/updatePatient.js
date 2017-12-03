function updatePatient() {

    $(".updatePatient").click(function() {
        var data = {
            name:$("#up-name").val(),
            email:$("#up-email").val(),
            password:"secret",
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
            lastVisitDate:""
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
              alert('Post success')
            }
        });
    })
}

$(document).ready(function() {
    updatePatient()
});
