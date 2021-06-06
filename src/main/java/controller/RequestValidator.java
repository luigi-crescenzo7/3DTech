package controller;

import model.Utente.Utente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    /**
     * @param name Il nome del parametro che può essere presente nella request
     * @param errorMessage Il messaggio di errore da mostrare allo user
     */
    private void isRequired(String name, String errorMessage) {
        String parameter = request.getParameter(name);
        if (parameter == null || parameter.isBlank()) {
            list.add(errorMessage);
        }
    }

    /**
     * @param session   The session object obtained by the request
     * @param attribute The attribute name of the value stored in the session
     */
    public static void authenticate(HttpSession session, String attribute) {
        if (session == null || session.getAttribute(attribute) == null)
            throw new RequestNotValidException("Utente non autenticato");
    }

    /**
     * @param session
     * @param attribute
     */
    public static void authorization(HttpSession session, String attribute) {
        authenticate(session, attribute);
        Utente user = (Utente) session.getAttribute(attribute);
        if (!user.isAdmin())
            throw new RequestNotValidException("Utente non autorizzato");
    }

    /**
     * @param request
     * @throws RequestNotValidException
     */
    public static void validateRequest(HttpServletRequest request) throws RequestNotValidException {
        RequestValidator validator = new RequestValidator(request);
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String parameter = enumeration.nextElement();
            validator.isRequired(parameter, "Error message");
        }

        if (!validator.isRequestValid()) {
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