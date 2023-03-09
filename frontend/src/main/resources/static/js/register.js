const signUpBtn = document.querySelector(".sign-up-btn");
const popUpForm = document.querySelector(".popup-form");
const body = document.body;
const eyeIcon = document.querySelectorAll(".eye-icon");
const passwordInput = document.querySelectorAll(".password-input");

console.log(passwordInput);

function showPassword() {
    for (let i=0; i<eyeIcon.length; i++) {
        eyeIcon[i].addEventListener("click", function() {
            passwordInput[i].classList.toggle("show-password");
            if (passwordInput[i].classList.contains("show-password")) {
                passwordInput[i].type = "text";
            } else {
                passwordInput[i].type = "password";
            }
        })
    }
}

showPassword();




