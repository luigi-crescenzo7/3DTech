$(document).ready(function () {
    const divElement = $("#div-test")
    $("#fieldProductCategory").change(function () {
        const path = $('#ctxPath').val()
        console.log(path)
        const category = $("#fieldProductCategory").val()
        if (typeof (category) === "string") {
            switch (category) {
                case "Materiale plastico":
                    divElement.load(path + '/form-prodotti/materiale-plastico.jsp',
                        () => console.log("Success on " + category))
                    break;
                case "Stampanti 3D":
                    divElement.load(path + '/form-prodotti/stampanti-3d.jsp',
                        () => console.log("Success on " + category))
                    break;
                case "":
                    break;
                default:
                    console.log("errore!!")
                    break;
            }
        }
    })
})