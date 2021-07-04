package model.Utente;

import model.Utente.Utente;

import javax.servlet.http.HttpSession;

public class UserSession {
    private Utente user;

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
