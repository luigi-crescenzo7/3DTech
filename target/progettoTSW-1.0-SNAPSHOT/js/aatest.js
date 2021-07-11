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
    let file = $('#feldImage')
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


            console.log('ktmmm')
        })
    })

    function loadInputs(json) {
        switch (json.Categoria) {
            case "Materiale plastico":
                const div = $('#div-test-2')
                div.empty()
                div.append('<label for="fieldProductColor">Colore:</label>' +
                    '<input type="text" class="input" id="fieldProductColor" name="productColor">' +
                    '<br><br>' +
                    '<label for="fieldProductTemp">Temperatura (max):</label>' +
                    '<input type="number" class="input" id="fieldProductTemp" name="productTemp">' +
                    '<br><br>')

                $('#fieldProductColor').val(json.Caratteristiche.colore)
                $('#fieldProductTemp').val(json.Caratteristiche.temperatura)
                break
            case "Stampanti 3D":
                /*divElement.load(path + '/form-prodotti/stampanti-3d.jsp',
                    (response, status) => console.log(status))*/
                break
            default:
                console.log('error')
                break
        }
    }
})