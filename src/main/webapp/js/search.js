$(document).ready(function () {
    const contextPath = $('#ctxPath').val()
    $(".search-bar").bind('input', function (event) {
            let elem = $('#tags')
            $.post(contextPath + "/search/", {textContent: event.target.value}, function (data) {
                elem.autocomplete({
                    source: data,
                    delay: 50,
                    autofocus: true,
                })
            })
        }
    )
})