$(document).ready(function () {
    const path = $('#ctxPath').val()
    const element = document.getElementById('list')
    const divElement = $('#div-test-2')
    let nome2 = $('#nome2')
    let descrizione2 = $('#descrizione2')
    let marchio2 = $('#marchio2')
    let prezzo2 = $('#prezzo2')
    let sconto2 = $('#sconto2')
    let peso2 = $('#peso2')
    let productId = $('#product-id')
    let cat = $('#fieldProductCategry')
    let file = $('#hidden-file')
    $('#list').bind('change', function () {
        const id = element.options[element.selectedIndex].text
        console.log(id)
        $.post(path + '/controlpanel/get', {productId: id}, function (data) {
            console.log(data)
            console.log(data.Categoria)
            loadInputs(data)


            cat.val(data.Categoria)
            productId.val(id)
            nome2.val(data.Nome)
            descrizione2.val(data.Descrizione)
            marchio2.val(data.Marchio)
            prezzo2.val(data.Prezzo)
            sconto2.val(data.Sconto)
            peso2.val(data.Peso)

        })
    })

    function loadInputs(json) {
        const div = $('#div-test-2')
        switch (json.Categoria) {
            case "Materiale plastico":
                div.empty()
                div.append('<label for="fieldProductColor">Colore:</label>' +
                    '<input type="text" class="input" id="fieldProductColor" name="productColor">' +
                    '<br><br>' +
                    '<label for="fieldProductTemp">Temperatura (max):</label>' +
                    '<input type="number" class="input" id="fieldProductTemp" name="productTemp">' +
                    '<br><br>' +
                    '')


                $('#fieldProductColor').val(json.Caratteristiche.Colore)
                $('#fieldProductTemp').val(json.Caratteristiche.Temperatura)
                break
            case "Stampanti 3D":
                let vals = '<label for="fieldMaxSpeed">Velocita max</label>\n' +
                    '<input id="fieldMaxSpeed" name="productMaxSpeed" class="input" type="number">\n' +
                    '<br>\n' +
                    '<br>\n' +
                    '<label for="fieldSize">Dimensioni</label>\n' +
                    '<input id="fieldSize" name="productSize" class="input" type="text">\n' +
                    '<br>\n' +
                    '<br>'
                div.empty()
                div.append(vals)

                $('#fieldMaxSpeed').val(json.Caratteristiche['Velocità max'])
                $('#fieldSize').val(json.Caratteristiche.Dimensioni)
                break
            case "Resine":
                let input = '<label for="fieldProductHardness">Durezza:</label>\n' +
                    '<input type="text" class="input" id="fieldProductHardness" name="productHardness">\n' +
                    '<br>\n' +
                    '<br>'
                div.empty()
                div.append(input)
                $('#fieldProductHardness').val(json.Caratteristiche.Durezza)
                break
            default:
                div.empty()
                break
        }
        let imgElement = document.createElement('img')
        imgElement.src = path + '/images/' + json.Immagine
        imgElement.style.width = '100px'
        imgElement.style.height = '100px'
        imgElement.value = div.append(imgElement)
        div.append('<p>' + json.Immagine + '</p>')
        file.val(json.Immagine)
    }
})