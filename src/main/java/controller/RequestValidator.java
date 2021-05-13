package controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class RequestValidator {
    private final HttpServletRequest request;
    private final List<String> list;

    public RequestValidator(HttpServletRequest request) {
        this.request = request;
        this.list = new ArrayList<>();
    }

    private void isRequired(String name, String errorMessage) {
        String parameter = request.getParameter(name);
        if (parameter == null || parameter.isEmpty()) {
            list.add(errorMessage);
        }
    }

    public static void validateRequest(HttpServletRequest request) throws RequestNotValidException {
        RequestValidator validator = new RequestValidator(request);
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String parameter = enumeration.nextElement();
            validator.isRequired(parameter, "Error message");
        }

        if(!validator.isRequestValid()){
            request.setAttribute("errors", validator.getList());
            throw new RequestNotValidException();
        }
    }

    private boolean isRequestValid() {
        return list.isEmpty();
    }

    private List<String> getList() {
        return list;
    }
}