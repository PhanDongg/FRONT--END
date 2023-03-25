$(document).ready( function () {
    $('#productTable').DataTable();
    
    function remove() {
        $("#productTable").on("click", ".remove-btn", function() {
            let productId = $(this).closest("tr").attr("id");
            $(this).closest("tr").remove();
        })
    }

    remove();
});