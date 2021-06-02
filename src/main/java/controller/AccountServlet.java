package controller;


import model.Utente.Utente;
import model.Utente.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/xx/*")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = "/";

        RequestValidator.validateRequest(request);
        String contextPath = request.getContextPath();
        UtenteDAO dao = new UtenteDAO();
        HttpSession session = request.getSession();
        List<String> list;
        Utente user = null;
        RequestDispatcher dispatcher;

        switch (path) {
            case "/loginadmin":
                list = FormExtractor.retrieveParameterValues(request);
                user = FormExtractor.extractLogin(list);
                user = dao.doRetrieveEmailPassword(user);
                if (user.isAdmin()) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/results/controlpanel.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/results/usernotallowed.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/controlpanel":
                System.out.println("Hello");
                break;
            case "/registration":
                list = FormExtractor.retrieveParameterValues(request);
                user = FormExtractor.extractRegistration(list);
                dao.doSave(user);
                session.setAttribute("user", user);
                resource = "/index.jsp";
                response.sendRedirect(request.getContextPath() + resource);
                break;
            case "/login":
                list = FormExtractor.retrieveParameterValues(request);
                user = FormExtractor.extractLogin(list);
                user = dao.doRetrieveEmailPassword(user);
                if (user == null) {
                    System.out.println("Utente non esistente");
                    return;
                }
                session.setAttribute("user", user);
                resource = "/index.jsp";
                response.sendRedirect(request.getContextPath() + resource);
                break;
            case "logout":
                session.removeAttribute("user");
                resource = "/index.jsp";
                response.sendRedirect(request.getContextPath() + resource);
                break;
            default:
                response.sendError(404, "Not found");
                break;
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = "/";
        RequestDispatcher dispatcher;

        switch (path) {
            case "/admin":
                resource = "/WEB-INF/results/admin.jsp";
            case "/controlpanel":
                resource = "/WEB-INF/results/controlpanel.jsp";
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
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
             /* dispatcher = request.getRequestDispatcher("/WEB-INF/results/exceptions/notfound.jsp");
                dispatcher.forward(request, response);*/
                resource = "/WEB-INF/results/exceptions/notfound.jsp";
                break;
        }

        dispatcher = request.getRequestDispatcher(resource);
        dispatcher.forward(request, response);
    }
}