package model.Utente;

import javax.servlet.http.HttpSession;

public class UserSession {
    public static Utente getUserFromSession(HttpSession session, String parameter) {
        if (session != null && session.getAttribute(parameter) != null) {
            Object object = session.getAttribute(parameter);
            if (object instanceof Utente) {
                return (Utente) object;
            }
        }
        return null;
    }
}
