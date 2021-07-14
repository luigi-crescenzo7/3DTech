$(document).ready(function () {
    let contextPath = $('#ctxPath').val()
    let content = $('.orders-content')

    $('#btn-submit').click(function () {
        console.log("called")
        let id = $('#users-list option:selected').val()
        console.log(id)

        $.get(contextPath + '/controlpanel/orders', {userId: id}, function (data) {
            console.log(data)
            console.log(data.arr)

            for (let index = 0; index < data.arr.length; index++) {
                content.append('<details>' +
                    '<summary>Order id: ' + data.arr[index].id + '</summary>' +
                    '' + data.arr[index].carrello.prodotti[index].prodotto.nome+
                    '</details>')
            }
        })
    })
})