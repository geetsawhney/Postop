function getBooleanFromCheckValue(val) {
    var dict = {
        "on": true,
        "off": false
    }
    console.log(dict[val])
    return dict[val]
}
function checkValues()
{
    var errorInit
    var finalErrors="Check "
    var reEmail = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    var emailCheck = reEmail.test($("#cb-email").val())
    if(emailCheck==0)
    {
      errorInit="Email"
      finalErrors=finalErrors.concat(errorInit)
    }
    var reGender = /M|F/
    var genderCheck = reGender.test($("#cb-sex").val())
    if(genderCheck==0)
    {
      errorInit=", Gender"
      finalErrors=finalErrors.concat(errorInit)
    }
    var reDOB= /^(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$/
    var dobCheck = reDOB.test($("#cb-date").val())
    if(dobCheck==0)
    {
      errorInit=", Date of Birth"
      finalErrors=finalErrors.concat(errorInit)
    }

    var reLastVisitDate= /^(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$/
    var lastVisitDateCheck = reLastVisitDate.test($("#cb-last-visit-date").val())
    if(lastVisitDateCheck==0)
    {
      errorInit=", Last Visit Date"
      finalErrors=finalErrors.concat(errorInit)
    }
    addressCheck=1
    if(!$("#cb-address").val())
    {
      addressCheck=0
      errorInit=", Address"
      finalErrors=finalErrors.concat(errorInit)
    }

    hospitalVisitReasonCheck=1
    if(!$("#cb-hospital-visit-reason").val())
    {
      hospitalVisitReasonCheck=0
      errorInit=", Hospital Visit Reason"
      finalErrors=finalErrors.concat(errorInit)
    }

    passwordCheck=1
    if(!$("#cb-password").val())
    {
      passwordCheck=0
      errorInit=", Password"
      finalErrors=finalErrors.concat(errorInit)
    }

    var reName= /^[a-z ,.'-]+$/i
    var nameCheck = reName.test($("#cb-name").val())
    if(nameCheck==0)
    {
      errorInit=", Name"
      finalErrors=finalErrors.concat(errorInit)
    }
    var reUtiVisitCount= /[0-9]+/
    var utiVisitCountCheck = reUtiVisitCount.test($("#cb-uti-visit-count").val())
    if(utiVisitCountCheck==0)
    {
      errorInit=", UTI Visit Count"
      finalErrors=finalErrors.concat(errorInit)
    }

    var rePhone= /[0-9]{10}/
    var phoneCheck = rePhone.test($("#cb-phone").val())
    if(phoneCheck==0)
    {
      errorInit=", Phone"
      finalErrors=finalErrors.concat(errorInit)
    }

    if(emailCheck==1 && genderCheck==1 && phoneCheck==1 && dobCheck==1 && lastVisitDateCheck==1 && addressCheck==1 && hospitalVisitReasonCheck==1 && utiVisitCountCheck==1 && passwordCheck==1 && nameCheck==1 )
    {
      return 1;
    }
    else {
      alert(finalErrors)
    }
}
function submitPatient() {

    $("#submit-patient-btn").click(function() {
      var checkVal = checkValues();
      var fullAddress = $("#cb-address").val() + "|" + $("#cb-address2").val();
      if(checkVal==1)
      {
        var data = {
            name:$("#cb-name").val(),
            email:$("#cb-email").val(),
            password:$("#cb-password").val(),
            ssn:"",
            id:"",
            sex:$("#cb-sex").val(),
            dob:$("#cb-date").val(),
            address:fullAddress,
            phone:$("#cb-phone").val(),
            hospitalVisitReason:$("#cb-hospital-visit-reason").val(),
            utiVisitCount:$("#cb-uti-visit-count").val(),
           // catheterUsage: getBooleanFromCheckValue($("#cb-uses-catheter").val()),
            catheterUsage: document.getElementById("cb-uses-catheter").checked,
          // diabetic: getBooleanFromCheckValue($("#cb-is-diabetic").val()),
            diabetic: document.getElementById("cb-is-diabetic").checked,
            lastVisitDate:$("#cb-last-visit-date").val()
          }

        console.log(data)

        var postRequest = $.ajax({
            type: 'POST',
            url: '/api/v1/patient',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(data) {
                alert('Post success')
                location.reload();
            }
        });
        // postRequest.error(function() {
        //     alert('Error')
        // })
    }})

}

$(document).ready(function() {
    submitPatient()
});
