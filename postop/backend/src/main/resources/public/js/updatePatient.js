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
    var emailCheck = reEmail.test($("#up-email").val())
    if(emailCheck==0)
    {
      errorInit="Email"
      finalErrors=finalErrors.concat(errorInit)
    }
    var reGender = /M|F/
    var genderCheck = reGender.test($("#up-sex").val())
    if(genderCheck==0)
    {
      errorInit=", Gender"
      finalErrors=finalErrors.concat(errorInit)
    }
    var reDOB= /^(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$/
    var dobCheck = reDOB.test($("#up-date").val())
    if(dobCheck==0)
    {
      errorInit=", Date of Birth"
      finalErrors=finalErrors.concat(errorInit)
    }

    var reLastVisitDate= /^(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$/
    var lastVisitDateCheck = reLastVisitDate.test($("#up-last-visit-date").val())
    if(lastVisitDateCheck==0)
    {
      errorInit=", Last Visit Date"
      finalErrors=finalErrors.concat(errorInit)
    }
    addressCheck=1
    if(!$("#up-address").val())
    {
      addressCheck=0
      errorInit=", Address"
      finalErrors=finalErrors.concat(errorInit)
    }

    hospitalVisitReasonCheck=1
    if(!$("#up-hospital-visit-reason").val())
    {
      hospitalVisitReasonCheck=0
      errorInit=", Hospital Visit Reason"
      finalErrors=finalErrors.concat(errorInit)
    }

    // passwordCheck=1
    // if(!$("#up-password").val())
    // {
    //   passwordCheck=0
    //   errorInit=", Password"
    //   finalErrors=finalErrors.concat(errorInit)
    // }

    var reName= /^[a-z ,.'-]+$/i
    var nameCheck = reName.test($("#up-name").val())
    if(nameCheck==0)
    {
      errorInit=", Name"
      finalErrors=finalErrors.concat(errorInit)
    }
    var reUtiVisitCount= /[0-9]+/
    var utiVisitCountCheck = reUtiVisitCount.test($("#up-uti-visit-count").val())
    if(utiVisitCountCheck==0)
    {
      errorInit=", UTI Visit Count"
      finalErrors=finalErrors.concat(errorInit)
    }

    var rePhone= /[0-9]{10}/
    var phoneCheck = rePhone.test($("#up-phone").val())
    if(phoneCheck==0)
    {
      errorInit=", Phone"
      finalErrors=finalErrors.concat(errorInit)
    }

    if(emailCheck==1 && genderCheck==1 && phoneCheck==1 && dobCheck==1 && lastVisitDateCheck==1 && addressCheck==1 && hospitalVisitReasonCheck==1 && utiVisitCountCheck==1 && nameCheck==1 )
    {
      return 1;
    }
    else {
      alert(finalErrors)
    }
}

function updatePatient() {
    console.log("here!")
      var checkVal = checkValues();
      if(checkVal==1)
      {
      console.log("inside update patient")
        var data = {
            name:$("#up-name").val(),
            email:$("#up-email").val(),
            ssn:"",
            id:$("#up-deviceId").val(),
            sex:$("#up-sex").val(),
            dob:$("#up-date").val(),
            address:$("#up-address").val(),
            phone:$("#up-phone").val(),
            hospitalVisitReason:$("#up-hospital-visit-reason").val(),
            utiVisitCount:$("#up-uti-visit-count").val(),
            catheterUsage:getBooleanFromCheckValue($("#up-uses-catheter").val()),
            //catheterUsage: true,
            diabetic:getBooleanFromCheckValue($("#up-is-diabetic").val()),
            //diabetic: true,
            lastVisitDate:$("#up-last-visit-date").val()
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
  }  //})
}

$(document).ready(function() {
    console.log("here....")
    //updatePatient()
});
