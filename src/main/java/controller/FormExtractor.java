package controller;


import model.Utente.Utente;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
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
    //todo: usare getParameterMap
    public static Utente extractRegistration(List<String> values) {
        Utente user = new Utente();
        System.out.println(values);
        user.setEmail(values.get(0));
        user.setName(values.get(1));
        user.setSurname(values.get(2));
        user.setPasswordhash(values.get(3));
        user.setDataNascita(Date.valueOf(LocalDate.now())); //todo: cambiare
        user.setPhoneNumber(values.get(4));
        user.setZIPCode(values.get(7));
        user.setCity(values.get(6));
        user.setStreet(values.get(5));
        return user;
    }
}
