const page = document.querySelectorAll(".page-link");
const popup = document.querySelector(".popup-form");
const addCartBtn = document.querySelectorAll(".add-cart-btn");

for (let i = 0; i < addCartBtn.length; i++) {
    addCartBtn[i].addEventListener("click", showPopup);
}

function showPopup() {
    for (let i=0; i<addCartBtn.length; i++) {
        popup.classList.add("is-show");
        console.log()
    }
}

document.addEventListener("click", closePopup);

function closePopup() {
    // if (!popup.contains(e.target) && !e.target.matches(".add-cart-btn")) {
    //     console.log("ko chứa");
    //     popup.classList.remove("is-show");
    // }
    if (popup) {
        popup.classList.remove("is-show");
    }
}


