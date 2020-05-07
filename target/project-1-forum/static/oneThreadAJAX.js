$(document).ready(function(){
    var deleteButtons = $(".button-delete");
    $(".button-delete").on("click", function(event){
        var xhr = new XMLHttpRequest();
        var url = "/thread/delete/" + this.getAttribute("data-thread-id");

        event.stopPropagation();
        event.stopImmediatePropagation();

        xhr.open("DELETE", url, true);
        xhr.onreadystatechange = function() {
            console.log(this.status);
            if (this.readyState == 4) {
                console.log("readyState is 4");
                if (this.status == 200) {
                    console.log("status is 200");
                    $(location).attr('href','/forums');
                }
            }
        };
        xhr.send();
    });
});
