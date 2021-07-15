$(document).ready(function () {
    const divElement = $("#div-test")
    $("#fieldProductCategory").change(function () {
        const path = $('#ctxPath').val()
        const category = $("#fieldProductCategory").val()
        if (typeof (category) === "string") {
            switch (category) {
                case "Materiale plastico":
                    divElement.load(path + '/form-prodotti/materiale-plastico.jsp',
                        (response, status) => console.log(status))
                    break;
                case "Stampanti 3D":
                    divElement.load(path + '/form-prodotti/stampanti-3d.jsp',
                        (response, status) => console.log(status))
                    break;
                case "Resine":
                    divElement.load(path + '/form-prodotti/resine.jsp',
                        (response, status) => console.log(status))
                    break;
                default:
                    break;
            }
        }
    })
})