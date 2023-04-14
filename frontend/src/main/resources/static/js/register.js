
const userName = $("#userName");
const fullName = $("#fullName");
const email = $("#email");
const phone = $("#phone");
const password = $("#password");
const passwordconfirm = $("#passwordconfirm");
const inputItem = $('.input-item');
const form = $('form');

var check = true;

//show password
$(document).ready(function() {


	$('.eye-icon').click(function() {
		$(this).children('i').toggleClass('bi bi-eye');

		if (!$(this).hasClass('show')) {
			$(this).prev().attr('type', 'text');
			$(this).addClass('show');
		} else if ($(this).hasClass('show')) {
			$(this).prev().attr('type', 'password');
			$(this).removeClass('show');
		}
	});

	form.submit(function(event) {
		let isValid = checkValidate();
		if (isValid) {
			console.log(check);
		} else {
			event.preventDefault();
			check = false;
			console.log(check);
		}
	});

	$('#register').click(function() {
		Array.from(inputItem).map((ele) =>
			ele.classList.remove('success')
		);
		let isValid = checkValidate();

		//		if (isValid) {
		////			alert('Đăng ký thành công');
		//		} else {
		////			alert('Đăng ký thất bại');
		//			checkValidate();
		//		}

		if (isValid == false) {
			alert('Đăng ký thất bại');
			checkValidate();
		} else {
			alert('Đăng ký thành công');
			checkValidate();
		}

	});

});


//validation bằng js
function checkValidate() {
	let usernameValue = userName.val();
	let fullnameValue = fullName.val();
	let emailValue = email.val();
	let phoneValue = phone.val();
	let passwordValue = password.val();
	let passwordconfirmValue = passwordconfirm.val();

	let isCheck = true;

	//	console.log(usernameValue);
	//	console.log(fullnameValue);
	//	console.log(emailValue);
	//	console.log(phoneValue);
	//	console.log(passwordValue);
	//	console.log(passwordconfirmValue);

	if (usernameValue == '') {
		setError(userName, 'Tên tài khoản không được để trống');
		isCheck = false;
	} else if (usernameValue.length < 3) {
		setError(userName, 'Cần nhập ít nhất 2 kí tự');
		isCheck = false;
	} else {
		setSuccess(userName);
	}


	if (fullnameValue == '') {
		setError(fullName, 'Họ và Tên không được để trống');
		isCheck = false;
	} else {
		setSuccess(fullName);
	}

	if (emailValue == '') {
		setError(email, 'Email không được để trống');
		isCheck = false;
	} else if (!isEmail(emailValue)) {
		setError(email, 'Email không đúng định dạng');
		isCheck = false;
	} else {
		setSuccess(email);
	}

	if (phoneValue == '') {
		setError(phone, 'Số điện thoại không được để trống');
		isCheck = false;
	} else if (!isPhone(phoneValue)) {
		setError(phone, 'Số điện thoại không đúng định dạng');
		isCheck = false;
	} else {
		setSuccess(phone);
	}

	if (passwordValue == '') {
		setError(password, 'Vui lòng nhập Mật khẩu');
		isCheck = false;
	} else {
		setSuccess(password);
	}

	if (passwordconfirmValue == '' || passwordconfirmValue != passwordValue) {
		setError(passwordconfirm, 'Vui lòng xác nhận lại Mật khẩu');
		isCheck = false;
	} else {
		setSuccess(passwordconfirm);
	}

	return isCheck;

};


function isEmail(email) {
	return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}

function isPhone(number) {
	return /(0[3|5|7|8|9])+([0-9]{8})\b/.test(number);
}

function setSuccess(e) {
	e.closest('.input-item').find('.valid-message').html("");
	e.closest('.input-item').children(':nth-child(2)').removeClass('valid-message');
	e.closest('.input-item').addClass('success');
}

function setError(e, message) {
	e.closest('.input-item').children(':nth-child(2)').addClass('valid-message');
	e.closest('.input-item').find('.valid-message').html(message);
	e.closest('.input-item').addClass('error');
}



