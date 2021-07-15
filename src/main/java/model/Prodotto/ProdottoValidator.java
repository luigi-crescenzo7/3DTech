package model.Prodotto;

import model.utilities.RequestValidator;

import javax.servlet.http.HttpServletRequest;

public class ProdottoValidator {

    public static RequestValidator validateProduct(HttpServletRequest request) {
        RequestValidator validator = new RequestValidator(request);
        validator.assertDouble("productPrice", "Formato prezzo non valido");
        validator.assertDouble("productWeight", "Formato peso non valido");
        validator.assertDouble("productDiscount", "Formato sconto non valido");
        return validator;
    }

    public static RequestValidator validateStampante3D(HttpServletRequest request) {
        RequestValidator validator = new RequestValidator(request);
        validator.assertDouble("productPrice", "Formato prezzo non valido");
        validator.assertDouble("productWeight", "Formato peso non valido");
        validator.assertDouble("productDiscount", "Formato sconto non valido");
        validator.assertDouble("productMaxSpeed", "Formato velocità non valido");
        return validator;
    }

    public static RequestValidator validateResina(HttpServletRequest request) {
        RequestValidator validator = new RequestValidator(request);
        validator.assertDouble("productPrice", "Formato prezzo non valido");
        validator.assertDouble("productWeight", "Formato peso non valido");
        validator.assertDouble("productDiscount", "Formato sconto non valido");
        validator.assertDouble("productHardness", "Formato durezza non valido");
        return validator;
    }

    public static RequestValidator validateMaterialePlastico(HttpServletRequest request) {
        RequestValidator validator = new RequestValidator(request);
        validator.assertDouble("productPrice", "Formato prezzo non valido");
        validator.assertDouble("productWeight", "Formato peso non valido");
        validator.assertDouble("productDiscount", "Formato sconto non valido");
        validator.assertDouble("productTemp", "Formato temperatura non valido");
        return validator;
    }


    public static RequestValidator chooseProduct(String category, HttpServletRequest request) {
        RequestValidator validator = new RequestValidator(request);
        switch (category) {
            case "Materiale plastico":
                validator = validateMaterialePlastico(request);
                break;
            case "Stampanti 3D":
                validator = validateStampante3D(request);
                break;
            case "Resine":
                validator = validateResina(request);
                break;
            case "Accessori":
            case "Utensili":
            case "Ricambi":
                validator = validateProduct(request);
                break;
            default:
                System.out.println("error");
                break;
        }
        return validator;
    }
}
