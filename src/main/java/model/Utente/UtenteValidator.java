package model.Utente;

import controller.RequestValidator;

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
        validator.assertName("fieldName", "Il nome deve essere compreso tra 3 e 25 caratteri");
        validator.assertSurname("fieldSurname", "Cattiva composizione cognome");
        validator.assertPassword("fieldPassword", "Cattiva composizione password");
        validator.assertPhoneNumber("", "");
        return validator;
    }

}
