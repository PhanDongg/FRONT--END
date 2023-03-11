window.addEventListener("load", function() {

    // slider recommended products

    const slider = this.document.querySelector(".recommended-product-container");
    const sliderContainer = document.querySelector(".recommended-product-slide");
    const  prevBtn = document.querySelector(".prev-btn");
    const nextBtn = document.querySelector(".next-btn");
    const sliderItems = document.querySelectorAll(".recommended-product-slide-item");
    const sliderItemWidth = sliderItems[0].offsetWidth;
    const slidesLength = sliderItems.length;
    let pos = 0;
    let index = 0;

    nextBtn.addEventListener("click", function() {
        handleChangeSlide(1);
    })

    prevBtn.addEventListener("click", function() {
        handleChangeSlide(-1)
    })

    function handleChangeSlide(dir) {
        if (dir === 1) {
            if (index >= slidesLength - 1) {
                index = slidesLength - 1;
                return;
            }
            pos = pos - sliderItemWidth;
            sliderContainer.style = `transform: translateX(${pos}px)`;
            index++;
        } else if (dir === -1) { 
            if (index <= 0) {
                index = 0;
                return;
            }
            pos = pos + sliderItemWidth;
            sliderContainer.style = `transform: translateX(${pos}px)`;
            index--;
        }
    }


    // product qty control

    const addBtn = document.querySelectorAll(".add-qty-btn");
    const minusBtn = document.querySelectorAll(".minus-qty-btn");
    const qtyInput = document.querySelectorAll(".qty-input");
    
    handleAddBtn();
    handleMinusBtn();

    function handleAddBtn() {
        for (let i=0; i<addBtn.length; i++) {
            addBtn[i].addEventListener("click", function() {
                console.log("click" + i);
                qtyInput[i].value++;
            })
        }
    }

    function handleMinusBtn() {
        for (let i=0; i<minusBtn.length; i++) {
            minusBtn[i].addEventListener("click", function() {
                if (qtyInput[i].value == 1) {
                    return;
                }
                qtyInput[i].value--;
                
            })
        }
    }
})