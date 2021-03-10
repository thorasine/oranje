 $(document).ready(function() {
    $("#login-form").submit(function(e){
        if(typeof grecaptcha !== 'undefined' && grecaptcha.getResponse() == ""){
            console.log("empty captcha");
            $("#captcha-error").css("display", "")
            event.preventDefault();
        }
    });
});