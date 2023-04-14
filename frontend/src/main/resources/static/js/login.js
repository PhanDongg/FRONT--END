
const userName = $("#userName");
const password = $("#password");
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

	$('#login').click(function() {
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
			alert('Đăng nhập thất bại');
			checkValidate();
		}

	});

});


//validation bằng js
function checkValidate() {
	let usernameValue = userName.val();
	let passwordValue = password.val();

	let isCheck = true;


	if (usernameValue == '') {
		setError(userName, 'Vui lòng nhập Tên tài khoản hoặc Email');
		isCheck = false;
	} else if (usernameValue.length < 3) {
		setError(userName, 'Cần nhập ít nhất 2 kí tự');
		isCheck = false;
	} else if (checkSpecialCharacters(usernameValue) == true && !isEmail(usernameValue)) {
		setError(userName, 'Email không đúng định dạng');
		isCheck = false;
	} else {
		setSuccess(userName);
	}


	if (passwordValue == '') {
		setError(password, 'Vui lòng nhập Mật khẩu');
		isCheck = false;
	} else {
		setSuccess(password);
	}

	return isCheck;

};


function isEmail(email) {
	return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
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

var specialChars = "@";
function checkSpecialCharacters(string) {
	for (i = 0; i < specialChars.length; i++) {
		if (string.indexOf(specialChars[i]) > -1) {
			return true;
		}
	}
	return false;
}




