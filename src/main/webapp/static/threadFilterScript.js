$(document).ready(function() {
    $("#searchInput").on("keyup", function(){
        var value = $(this).val().toLowerCase();
        $('#thread-container a').filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});