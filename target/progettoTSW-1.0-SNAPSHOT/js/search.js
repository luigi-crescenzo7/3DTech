$(document).ready(function () {
    const contextPath = $('#path').val()
    console.log(contextPath)
    $(".search-bar").bind('input', function (event) {
            if (event.target.value !== '') {
                $.post(contextPath + "/search/", {textContent: event.target.value}, function (data) {
                    console.log(data)
                    $('#tags').autocomplete({
                        source: data,
                        delay: 100,
                        autofocus: true
                    })
                })
            }
        }
    )
})