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
            const json2 = jsonObj.caratteristiche
            let robe = ''
            console.log(jsonObj)
            const imgElement = document.createElement('img')
            imgElement.src = ctx + '/covers/' + jsonObj.immagine

            for (let property in json2) {
                if (json2.hasOwnProperty(property)) {
                    robe += '<p>' + property + ': ' + json2[property] + '</p>'
                    console.log(property + ' --- ' + json2[property])
                }
            }

            content.innerHTML = '<p>Id: ' + jsonObj.id + ' </p><p>Nome: ' + jsonObj.nome + ' </p>' +
                '<p>Marchio: ' + jsonObj.marchio + ' </p> <p>Prezzo: ' + jsonObj.prezzo + ' </p>' + robe

            content.appendChild(imgElement)
            console.log(content.querySelector('img'))
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