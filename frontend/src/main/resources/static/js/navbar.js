$(document).ready(function () {

    const userMenuTemplate =
        `<ul class="user-dropdown-menu">
            <li><a class="user-dropdown-item" href="#">Thông tin thành viên</a></li>
            <li><a class="user-dropdown-item" href="#">Admin dashboard</a></li>
            <li><a class="user-dropdown-item" href="#">Đăng xuất</a></li>
        </ul>`

    $(".user-menu").append(userMenuTemplate);

    $(".user-menu").on("click", function() {
        $(".user-dropdown-menu").toggleClass("show-user-menu");
    })

    $(".user-menu").on("click", closeFromOutside)

    function closeFromOutside(e) {
        console.log(e.target);
        if (!(e.target).has(".user-dropdown-menu")) {
            $(".user-dropdown-menu").remove("show-user-menu");
        }
    }
})