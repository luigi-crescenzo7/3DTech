package controller;

import model.utilities.Cart;
import model.utilities.FormExtractor;
import model.Utente.Utente;
import model.Utente.UtenteDAO;
import model.Utente.UtenteValidator;
import model.utilities.RequestNotValidException;
import model.utilities.RequestValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


@WebServlet(urlPatterns = "/account/*")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource;

        String contextPath = request.getContextPath();
        UtenteDAO dao = new UtenteDAO();
        HttpSession session = request.getSession();
        Utente user;
        Map<String, String[]> map = request.getParameterMap();

        try {
            switch (path) {
                case "/loginadmin":
                    UtenteValidator.validateLogin(request).hasErrors();
                    user = FormExtractor.extractLogin(map);
                    user = dao.doRetrieveEmailPassword(user);
                    if (user == null) {
                        request.setAttribute("errorMessages", "Account inesistente");
                        request.getRequestDispatcher("/WEB-INF/results/loginadmin.jsp").forward(request, response);
                        return;
                    }

                    if (user.isAdmin()) {
                        resource = "/controlpanel/";
                        session.setAttribute("userSession", user);
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Utente non autorizzato");
                        return;
                    }
                    break;
                case "/registration":
                    request.setAttribute("returnBack", "registration.jsp");
                    UtenteValidator.validateRegistration(request).hasErrors();
                    user = FormExtractor.extractRegistration(map);
                    dao.doSave(user);
                    session.setAttribute("userSession", user);
                    resource = "/index.jsp";
                    break;
                case "/login":
                    request.setAttribute("returnBack", "login.jsp");
                    UtenteValidator.validateLogin(request).hasErrors();
                    user = FormExtractor.extractLogin(map);
                    user = dao.doRetrieveEmailPassword(user);
                    if (user == null) {
                        request.setAttribute("errorMessages", "Account inesistente");
                        request.getRequestDispatcher("/WEB-INF/results/login.jsp").forward(request, response);
                        return;
                    }
                    session.setAttribute("userSession", user);
                    session.setAttribute("sessionCart", new Cart(new ArrayList<>()));
                    resource = "/index.jsp";

                    break;
                case "/logout":
                    request.setAttribute("returnBack", "index.jsp");
                    RequestValidator.authenticate(session, "userSession");
                    session.invalidate();
                    resource = "/index.jsp";
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
            }
        } catch (RequestNotValidException e) {
            e.dispatchErrors(request, response);
            return;
        }
        response.sendRedirect(contextPath + resource);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource;

        switch (path) {
            case "/admin":
                resource = "/WEB-INF/results/loginadmin.jsp";
                break;
            case "/controlpanel":
                resource = "/WEB-INF/results/admin-dashboard.jsp";
                break;
            case "/login":
                resource = "/WEB-INF/results/login.jsp";
                break;
            case "/registration":
                resource = "/WEB-INF/results/registration.jsp";
                break;
            case "/profile":
                resource = "/WEB-INF/results/account.jsp";
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(resource);
        dispatcher.forward(request, response);
    }
}