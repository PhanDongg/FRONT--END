//show password
$(document).ready(function(){
    $('#eye').click(function(){
        $(this).toggleClass('open');
        $(this).children('i').toggleClass('fa-eye-slash fa-eye');
        if($(this).hasClass('open')) {
            $(this).prev().attr('type', 'text');
        } else {
            $(this).prev().attr('type', 'password');
        }
    });
});

//validation bằng js
$('#register').click(function validate() {
	
    var firstName = document.getElementById("userName").value;
    if (firstName.length === 0) {
        $('#error1').html("Vui lòng nhập tên tài khoản.");
    }
    
    var lastName = document.getElementById("fullName").value;
    if (lastName.length === 0) {
        $('#error2').html("Vui lòng nhập Họ và Tên.");
    }
    
    var email = document.getElementById("email").value;
    if (email.length === 0) {
        $('#error3').html("Vui lòng nhập địa chỉ email chính xác của bạn.");
       
    }
    
    var email = document.getElementById("email").value;
    var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if (!emailRegex.test(email)) {
        $('#error3').html("Vui lòng nhập địa chỉ email chính xác của bạn.");
       
    }
    var phone = document.getElementById("phone").value;
    var phoneRegex = /^\d{3}-\d{3}-\d{6}$/;
    if (!phoneRegex.test(phone)) {
        $('#error4').html("Vui lòng nhập đúng số điện thoại của bạn.");
        
    }

    var password = document.getElementById("password").value;
    if (password.length === 0) {
        $('#error5').html("Vui lòng nhập mật khẩu.");
        
    }

    var passwordconfirm = document.getElementById("passwordconfirm").value;
    if (passwordconfirm.length === 0) {
        $('#error6').html("Vui lòng nhập lại để xác nhận mật khẩu.");
        
    }
    
}
);