package model.Utente;

import controller.RequestValidator;

import javax.servlet.http.HttpServletRequest;

public class UtenteValidator {

    public static RequestValidator validateLogin(HttpServletRequest request) {
        RequestValidator validator = new RequestValidator(request);
        validator.assertEmail("fieldEmail", "Cattiva composizione email");
        validator.assertPassword("fieldPassword", "..");
        return validator;
    }

    public static RequestValidator validateRegistration(HttpServletRequest request) {
        RequestValidator validator = new RequestValidator(request);
        validator.assertName("", "Il nome deve essere compreso tra 3 e 25 caratteri");
        validator.assertEmail("", "Email eror");
        return validator;
    }
}
