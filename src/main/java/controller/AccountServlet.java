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

        System.out.println(path);
        RequestValidator.validateRequest(request);
        String contextPath = request.getContextPath();
        UtenteDAO dao = new UtenteDAO();
        HttpSession session = request.getSession();
        List<String> list;
        Utente user = null;
        boolean flag = true;

        switch (path) {
            case "/loginadmin":
                list = FormExtractor.retrieveParameterValues(request);
                user = FormExtractor.extractLogin(list);
                user = dao.doRetrieveEmailPassword(user);

                if (user == null)
                    resource = "";

                if (user.isAdmin()) {
                    resource = "/controlpanel/";
                    session.setAttribute("user", user);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    flag = false;
                }
                break;
            case "/registration":
                list = FormExtractor.retrieveParameterValues(request);
                user = FormExtractor.extractRegistration(list);
                dao.doSave(user);
                session.setAttribute("user", user);
                resource = "/index.jsp";
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
                break;
            case "/logout":
                session.removeAttribute("user");
                resource = "/index.jsp";
                break;
            default:
                flag = false;
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
        if (flag) {
            System.out.println(resource);
            response.sendRedirect(contextPath + resource);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = "/";
        RequestDispatcher dispatcher;
        boolean flag = true;
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
                flag = false;
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }

        if (flag) {
            dispatcher = request.getRequestDispatcher(resource);
            dispatcher.forward(request, response);
        }
    }
}