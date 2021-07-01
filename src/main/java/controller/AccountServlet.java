package controller;

import model.Utente.Utente;
import model.Utente.UtenteDAO;
import model.Utente.UtenteValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet(urlPatterns = "/xx/*")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = "/";

        System.out.println(path);
        String contextPath = request.getContextPath();
        UtenteDAO dao = new UtenteDAO();
        HttpSession session = request.getSession();
        List<String> list;
        Utente user = null;
        RequestValidator validator = null;
        Map<String, String[]> map = request.getParameterMap();

        try {
            switch (path) {
                case "/loginadmin":
                    validator = UtenteValidator.validateLogin(request);
                    if (!validator.hasErrors()) {
                        user = FormExtractor.extractLogin(map);
                        user = dao.doRetrieveEmailPassword(user);
                        if (user == null) {
                            request.setAttribute("errorMsg", "Account inesistente");
                            System.out.println("Email o password non validi");
                            request.getRequestDispatcher("/WEB-INF/results/loginadmin.jsp").forward(request, response);
                            return;
                        }

                        if (user.isAdmin()) {
                            resource = "/controlpanel/";
                            session.setAttribute("userSession", user);
                            System.out.println("Ok??");
                        } else {
                            System.out.println("WTF??");
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                            return;
                        }
                    }
                    break;
                case "/registration":
                    validator = UtenteValidator.validateRegistration(request);
                    if (!validator.hasErrors()) {
                        user = FormExtractor.extractRegistration(map);
                        dao.doSave(user);
                        session.setAttribute("userSession", user);
                        resource = "/index.jsp";
                    }
                    break;
                case "/login":
                    validator = UtenteValidator.validateLogin(request);
                    if (!validator.hasErrors()) {
                        user = FormExtractor.extractLogin(map);
                        user = dao.doRetrieveEmailPassword(user);
                        if (user == null) {
                            request.setAttribute("errorMsg", "Account inesistente");
                            System.out.println("Email o password non validi");
                            request.getRequestDispatcher("/WEB-INF/results/login.jsp").forward(request, response);
                            return;
                        }
                        session.setAttribute("userSession", user);
                        resource = "/index.jsp";
                    }
                    break;
                case "/logout":
                    session.removeAttribute("user");
                    session.invalidate();
                    resource = "/index.jsp";
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
            }
        } catch (RequestNotValidException e) {
            System.out.println("error??? " + e.getMessage());
            System.out.println(e.getErrors());
            e.dispatchErrors(request, response);
            return;
        }
        response.sendRedirect(contextPath + resource);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = "/";
        System.out.println("Path: " + path);

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
            case "/account":
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