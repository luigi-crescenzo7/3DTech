$(document).ready(function () {
    let modalElement = document.querySelector("#modal-box")
    let buttons = document.querySelectorAll('.show-info')
    let content = document.querySelector(".modal-content")
    let ctx = document.querySelector("#ctxPath").value

    function showModal() {
        let idProdotto = this.nextElementSibling.value
        $.post(ctx + '/controlpanel/product-info', {productId: idProdotto}, function (data) {
            const jsonObj = data
            console.log(data + '   ' + jsonObj)

            content.innerHTML = '<p>' + jsonObj.id + ' </p> <p>' + jsonObj.nome + ' </p>' +
                '<p>' + jsonObj.marchio + ' </p> <p>' + jsonObj.prezzo + ' </p>'

        })
        modalElement.style.display = "block"
    }

    for (let i = 0; i < buttons.length; i++) {
        buttons[i].addEventListener('click', showModal)
    }

    document.addEventListener('click', function (event) {
        if (event.target === modalElement)
            modalElement.style.display = "none";
    })
})