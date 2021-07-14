$(document).ready(function () {
        let modalElement = document.querySelector("#modal-box")
        let buttons = document.querySelectorAll('.show-info')
        let content = document.querySelector(".modal-content")
        let ctx = document.querySelector("#ctxPath").value
        let modalType = document.querySelector('input[name="modal-type"]').value
        let btnUser = document.querySelector("#orders-btn")


        if (modalType === 'user')
            btnUser.addEventListener('click', showUserOrders)

        for (let i = 0; i < buttons.length; i++) {
            if (modalType === 'product') {
                console.log("type product")
                buttons[i].addEventListener('click', showProduct)
            } else if (modalType === 'category') {
                console.log("type category")
                buttons[i].addEventListener('click', showCategory)
            }
        }

        function calcolaTotale(arr) {
            let total = 0
            for (let num = 0; num < arr.length; num++) {
                total += arr[num].quantita
            }
            return total
        }

        function getProducts(list) {
            let roba = ''
            for (let i = 0; i < list.length; i++) {
                roba += '<tr><td>' + list[i].prodotto.nome + '</td><td>  '
                    + list[i].prodotto.prezzo + '</td><td>' + list[i].quantita + '</td></tr>'
            }
            return roba
        }

        document.addEventListener('click', function (event) {
            if (event.target === modalElement)
                modalElement.style.display = "none";
        })


        function showUserOrders() {
            console.log("aaaaaaaaaaaa")
            let id = $('#users-list option:selected').val()
            $.get(ctx + '/controlpanel/orders', {userId: id}, function (data) {
                console.log(data)
                let values = ''

                for (let index = 0; index < data.length; index++) {
                    console.log(data[index].id)
                    values += '<details>' +
                        '<summary> ' +
                        'Id:' + data[index].id + ' Data Ordine: ' + data[index].dataOrdine +
                        ' Totale prodotti: ' + calcolaTotale(data[index].carrello.prodotti) +
                        '</summary>'
                        + '<table> <thead><tr><th>Nome</th><th>Prezzo</th><th>Quantita</th></tr> </thead>' +
                        '<tbody>' + getProducts(data[index].carrello.prodotti) + '</tbody>' +
                        '</table>' +
                        '</details>'
                }

                content.innerHTML = values
                modalElement.style.display = 'block'
            })
        }

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
            })
        }

        function showProduct() {
            let idProdotto = this.nextElementSibling.value
            $.post(ctx + '/controlpanel/product-info', {productId: idProdotto}, function (data) {
                console.log(data)
                const json2 = data.Caratteristiche
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

    }
)

