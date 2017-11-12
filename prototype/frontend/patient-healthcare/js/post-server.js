function getBooleanFromCheckValue(val) {
    var dict = {
        "on": true,
        "off": false
    }
    console.log(dict[val])
    return dict[val]
}

function submitPatient() {
    $("#submit-patient-btn").click(function() {
        var data = {
            name:$("#cb-name").val(),
            email:$("#cb-email").val(),
            password:$("#cb-password").val(),
            ssn:"",
            id:"",
            sex:$("#cb-sex").val(),
            dob:$("#cb-date").val(),
            address:$("#cb-address").val(),
            phone:$("#cb-phone").val(),
            hospitalVisitReason:$("#cb-hospital-visit-reason").val(),
            utiVisitCount:$("#cb-uti-visit-count").val(),
            //catheterUsage: true,
            //diabetic: true,
           catheterUsage: getBooleanFromCheckValue($("#cb-uses-catheter").val()),
          diabetic: getBooleanFromCheckValue($("#cb-is-diabetic").val()),
            lastVisitDate:$("#cb-last-visit-date").val()
          }

            /*email: $("#cb-email").val(),
            callbackDate: $("#cb-date").val(),
            severity: parseInt($("#cb-severity").val()),
            hasPain: getBooleanFromCheckValue($("#cb-has-pain").val()),
            hasNausea: getBooleanFromCheckValue($("#cb-has-nausea").val()),
            hasFever: getBooleanFromCheckValue($("#cb-has-fever").val()),
            hasFatigue: getBooleanFromCheckValue($("#cb-has-fatigue").val()),
            isResolved: getBooleanFromCheckValue($("#cb-is-resolved").val()),
            urineColor: $("#cb-urine-color").val()*/

        console.log(data)

        var postRequest = $.ajax({
            type: 'POST',
            url: 'http://10.194.107.57:8080/api/v1/patient',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(data) {
                alert('Post success')
            }
        });
        // postRequest.error(function() {
        //     alert('Error')
        // })
    })
}

$(document).ready(function() {
    submitPatient()
});
