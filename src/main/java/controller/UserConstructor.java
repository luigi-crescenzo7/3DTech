package controller;

import model.Utente;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public class UserConstructor {

    public static Utente extractRegistration(List<String> values) throws ParseException {
        Utente user = new Utente();
        user.setEmail(values.get(0));
        user.setPasswordhash(values.get(1));
        user.setName(values.get(2));
        user.setSurname(values.get(3));
        user.setDataNascita((Date.valueOf(values.get(4))));
        user.setPhoneNumber(values.get(5));
        user.setZIPCode(values.get(6));
        user.setCity(values.get(7));
        user.setStreet(values.get(8));
        return user;
    }

    public static Utente extractLogin(List<String> values){
        Utente user = new Utente();
        user.setEmail(values.get(0));
        user.setPasswordhash(values.get(1));
        return user;
    }
}
