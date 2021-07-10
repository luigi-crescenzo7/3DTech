$(document).ready(function () {
    const path = $('#ctxPath').val()
    const id = $('#id2').val()
    let nome2 = $('#nome2')
    let descrizione2 = $('#descrizione2')
    let marchio2 = $('#marchio2')
    let prezzo2 = $('#prezzo2')
    let sconto2 = $('#sconto2')
    let cat = $('#fieldProductCategry')
    let file = $('#feldImage')
    $('#id2').bind('change', function () {
        $.post(path + '/controlpanel/get', {productId: id}, function (data) {
            console.log(data)

            nome2.val(data.Nome)
            descrizione2.val(data.Descrizione)
            marchio2.val(data.Marchio)
            prezzo2.val(data.Prezzo)
            sconto2.val(data.Sconto)
            cat.val(data.Categoria)
        })
    })
})