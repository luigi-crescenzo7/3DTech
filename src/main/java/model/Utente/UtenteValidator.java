package model.Utente;

import model.utilities.RequestValidator;

import javax.servlet.http.HttpServletRequest;

public class UtenteValidator {

    public static RequestValidator validateLogin(HttpServletRequest request) {
        RequestValidator validator = new RequestValidator(request);
        validator.assertEmail("fieldEmail", "Cattiva composizione email");
        validator.assertPassword("fieldPassword", "Cattiva composizione password");
        return validator;
    }

    public static RequestValidator validateRegistration(HttpServletRequest request) {
        RequestValidator validator = new RequestValidator(request);
        validator.assertEmail("fieldEmail", "Cattiva composizione email");
        validator.assertPassword("fieldPassword", "Formato password non valido");
        validator.assertPhoneNumber("fieldPhoneNumber", "Numero non valido");
        validator.assertZIPCode("fieldZIPCode", "Formato CAP non valido");
        return validator;
    }
}