package model;


import model.Utente.Utente;

import java.sql.Date;
import java.util.Map;

public class FormExtractor {

    public static Utente extractLogin(Map<String, String[]> parameters) {
        Utente user = new Utente();
        user.setEmail(parameters.get("fieldEmail")[0]);
        user.setPasswordhash(parameters.get("fieldPassword")[0]);
        return user;
    }

    public static Utente extractRegistration(Map<String, String[]> parameters) {
        Utente user = new Utente();
        user.setEmail(parameters.get("fieldEmail")[0]);
        user.setName(parameters.get("fieldName")[0]);
        user.setSurname(parameters.get("fieldSurname")[0]);
        user.setPasswordhash(parameters.get("fieldPassword")[0]);
        user.setDataNascita(Date.valueOf(parameters.get("fieldDateOfBirth")[0]));
        user.setPhoneNumber(parameters.get("fieldPhoneNumber")[0]);
        user.setZIPCode(parameters.get("fieldZIPCode")[0]);
        user.setCity(parameters.get("fieldCity")[0]);
        user.setStreet(parameters.get("fieldStreet")[0]);
        return user;
    }
}
