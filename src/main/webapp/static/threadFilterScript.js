$(document).ready(function() {
    $("#searchInput").on("keyup", function(){
        var value = $(this).val().toLowerCase();
        $('#nav a').filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});