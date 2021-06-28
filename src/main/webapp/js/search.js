$(document).ready(function () {
    console.log("ciao")
    $("input[type='search']").bind('input', function (event) {
        console.log(event.target)
    })
})