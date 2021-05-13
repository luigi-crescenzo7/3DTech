package controller;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class FormExtractor {
    public static List<String> retrieveParameterValues(HttpServletRequest request) {
        List<String> list = new ArrayList<>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            list.add(request.getParameter(enumeration.nextElement()));
        }
        return list;
    }
}
