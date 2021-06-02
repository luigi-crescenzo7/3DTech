package controller;


import model.Utente.Utente;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class FormExtractor {
    public static List<String> retrieveParameterValues(HttpServletRequest request) {
        List<String> list = new ArrayList<>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements())
            list.add(request.getParameter(enumeration.nextElement()));
        return list;
    }

    //TODO possibile refactoring di extractLogin e extractRegistration in questa classe
    public static Utente extractLogin(List<String> values) {
        Utente user = new Utente();
        user.setEmail(values.get(0));
        user.setPasswordhash(values.get(1));
        return user;
    }


    /**
     * @param values
     * @return
     */
    public static Utente extractRegistration(List<String> values) {
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
}
