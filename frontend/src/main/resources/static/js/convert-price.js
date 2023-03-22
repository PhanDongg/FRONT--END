$(document).ready(function() {
	function convertPrice() {
        const priceStr = $(".dbPrice").text();
        const price = Number(Math.floor(priceStr));
        $(".dbPrice").text(price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
    }

    convertPrice();
})