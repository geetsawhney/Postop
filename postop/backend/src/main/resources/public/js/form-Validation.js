// Wait for the DOM to be ready
$().ready().function(){
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
  $("#new_Patient").validate({
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
      cb-name: "required",
      cb-address1: "required",
      cb-email: {
        required: true,
        // Specify that email should be validated
        // by the built-in "email" rule
        cb-email: true
      },
      password: {
        required: true,
        minlength: 5
      }
    },
    // Specify validation error messages
    messages: {
      cb-name: "Please enter your firstname",
      cb-address1: "Please enter your lastname",
      password: {
        required: "Please provide a password",
        minlength: "Your password must be at least 5 characters long"
      },
      cb-email: "Please enter a valid email address"
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
});
