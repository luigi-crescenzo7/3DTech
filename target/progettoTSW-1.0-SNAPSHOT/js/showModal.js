$(document).ready(function () {
        let modalElement = document.querySelector("#modal-box")
        let buttons = document.querySelectorAll('.show-info')
        let content = document.querySelector(".modal-content")
        let ctx = document.querySelector("#ctxPath").value;
        let modalType = document.querySelector('input[name="modal-type"]').value
        console.log(modalType)

        function showCategory() {
            let idCategoria = this.nextElementSibling.value
            $.post(ctx + '/controlpanel/category-info', {categoryId: idCategoria}, function (data) {
                    const imgElement = document.createElement('img')
                    imgElement.style.width = '100px'
                    imgElement.style.height = '100px'
                    imgElement.src = ctx + '/images/' + data.Immagine;

                    let values = '';
                    for (let property in data) {
                        if (data.hasOwnProperty(property))
                            values += '<p>' + property + ': ' + data[property] + '</p>'

                    }

                    content.innerHTML = values
                    content.appendChild(imgElement)
                    modalElement.style.display = "block"
                }
            )
        }

        function showProduct() {
            let idProdotto = this.nextElementSibling.value
            $.post(ctx + '/controlpanel/product-info', {productId: idProdotto}, function (data) {
                const json2 = data.caratteristiche
                let values = ''
                const imgElement = document.createElement('img')
                imgElement.style.width = '100px'
                imgElement.style.height = '100px'
                imgElement.src = ctx + '/images/' + data.Immagine

                for (let property in json2) {
                    if (json2.hasOwnProperty(property)) {
                        values += '<p>' + property + ': ' + json2[property] + '</p>'
                        console.log(property + ' --- ' + json2[property])
                    }
                }
                content.innerHTML = '<p>Id: ' + data.Id + ' </p><p>Nome: ' + data.Nome + ' </p>' +
                    '<p>Marchio: ' + data.Marchio + ' </p> <p>Prezzo: ' + data.Prezzo + ' </p>' + values

                content.appendChild(imgElement)
                modalElement.style.display = "block"
            })
        }

        for (let i = 0; i < buttons.length; i++) {
            if (modalType === 'product') {
                console.log("type product")
                buttons[i].addEventListener('click', showProduct)
            } else if (modalType === 'category') {
                console.log("type category")
                buttons[i].addEventListener('click', showCategory)
            }
        }

        document.addEventListener('click', function (event) {
            if (event.target === modalElement)
                modalElement.style.display = "none";
        })
    }
)