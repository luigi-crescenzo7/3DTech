package controller;

import model.Utente;

import java.util.List;

public class UserConstructor {

    public static Utente extractRegistration(List<String> values){
        Utente user = new Utente();
        user.setEmail(values.get(0));
        user.setPasswordhash(values.get(1));
        user.setName(values.get(2));
        user.setSurname(values.get(3));
        user.setPhoneNumber(values.get(4));
        user.setZIPCode(values.get(5));
        user.setCity(values.get(6));
        user.setStreet(values.get(7));
        return user;
    }

    public static Utente extractLogin(List<String> values){
        Utente user = new Utente();
        user.setEmail(values.get(0));
        user.setPasswordhash(values.get(1));
        return user;
    }
}
