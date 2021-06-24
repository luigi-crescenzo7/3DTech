package controller;

import model.Utente.Utente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

public class RequestValidator {
    private final HttpServletRequest request;
    private final List<String> list;
    private static final Pattern INT_PATTERN = Pattern.compile("^\\d*$");
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("^(-)?(0|[1-9]\\d+)\\.\\d+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    private static final Pattern PASSW_PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,16}$");
    private static final Pattern USR_NAME_PATTERN = Pattern.compile("^([a-zA-Z\\s]){3,25}$");

    public RequestValidator(HttpServletRequest request) {
        this.request = request;
        this.list = new ArrayList<>();
    }

    /**
     * @param name         Il nome del parametro che può essere presente nella request
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
        Object obj = session.getAttribute(attribute);
        if (obj instanceof Utente) {
            Utente user = (Utente) session.getAttribute(attribute);
            if (!user.isAdmin())
                throw new RequestNotValidException("Utente non autorizzato");
        } else {
            throw new RequestNotValidException("Errore");
        }
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

    private boolean required(String value) {
        return value != null && !value.isBlank();
    }

    private boolean isRequestValid() {
        return list.isEmpty();
    }

    public boolean assertMatch(String parameter, Pattern exp, String errorMessage) {
        String paramValue = request.getParameter(parameter);
        boolean flag = required(paramValue) && exp.matcher(paramValue).matches();

        if (!flag) {
            list.add(errorMessage);
            return false;
        } else {
            return true;
        }
    }

    public boolean assertEmail(String param, String msg) {
        return assertMatch(param, EMAIL_PATTERN, msg);
    }

    public boolean assertPassword(String param, String msg) {
        return assertMatch(param, PASSW_PATTERN, msg);
    }

    public boolean assertInt(String param, String msg) {
        return assertMatch(param, INT_PATTERN, msg);
    }

    public boolean assertDouble(String param, String msg) {
        return assertMatch(param, DOUBLE_PATTERN, msg);
    }

    public boolean assertName(String param, String msg) {
        return assertMatch(param, USR_NAME_PATTERN, msg);
    }

    private List<String> getList() {
        return list;
    }

    public void hasErrors() {
        if (!list.isEmpty())
            throw new RequestNotValidException(11);
    }
}