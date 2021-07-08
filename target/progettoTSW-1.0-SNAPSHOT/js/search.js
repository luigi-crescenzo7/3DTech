$(document).ready(function () {
    const contextPath = $('#ctxPath').val()
    console.log("Context path:" + contextPath)
    $(".search-bar").bind('input', function (event) {
            console.log(event.target.value)
            let elem = $('#tags')
            $.post(contextPath + "/search/", {textContent: event.target.value}, function (data) {
                console.log(data)
                elem.autocomplete({
                    source: data,
                    delay: 50,
                    autofocus: true,
                })
            })
        }
    )
})