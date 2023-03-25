$(document).ready(function () {

    const adminMenuTemplate =
        `<ul class="admin-dropdown-menu">
            <li><a class="admin-dropdown-item" href="#">Thông tin thành viên</a></li>
            <li><a class="admin-dropdown-item" href="#">Admin dashboard</a></li>
            <li><a class="admin-dropdown-item" href="#">Đăng xuất</a></li>
        </ul>`
    
    const userMenuTemplate =
        `<ul class="user-dropdown-menu">
            <li><a class="user-dropdown-item" href="#">Thông tin thành viên</a></li>
            <li><a class="user-dropdown-item" href="#">Đăng xuất</a></li>
        </ul>`


    $(".admin-menu").append(adminMenuTemplate);
    $(".user-menu").append(userMenuTemplate);

    $(".admin-menu").on("click", function() {
        $(".admin-dropdown-menu").toggleClass("show-admin-menu");
    })
    $(".user-menu").on("click", function() {
        $(".user-dropdown-menu").toggleClass("show-user-menu");
    })

/*    $(".admin-menu").on("click", closeFromOutside)

    function closeFromOutside(e) {
        console.log(e.target);
        if (!(e.target).has(".admin-dropdown-menu")) {
            $(".admin-dropdown-menu").remove("show-admin-menu");
        }
    }*/
})