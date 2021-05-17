package controller;

import model.Utente;
import model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet(urlPatterns = "/xx/*")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = "/";
        RequestValidator.validateRequest(request);
        RequestDispatcher dispatcher;
        UtenteDAO dao = new UtenteDAO();
        HttpSession session = request.getSession();
        List<String> list;
        Utente user = null;


        switch (path) {
            case "/registration":
                list = FormExtractor.retrieveParameterValues(request);
                try{
                    user = UserConstructor.extractRegistration(list);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dao.doSave(user);
                session.setAttribute("user", user);
                resource = "/index.jsp";
                response.sendRedirect(request.getContextPath() + resource);
                break;
            case "/login":
                list = FormExtractor.retrieveParameterValues(request);
                user = UserConstructor.extractLogin(list);
                user = dao.doRetrieveEmailPassword(user);
                if (user == null){
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

        switch (path) {
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
                response.sendError(404, "error");
                break;
        }

        if (path.equals("/login") || path.equals("/registration") || path.equals("/account")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(resource);
            dispatcher.forward(request, response);
        }
    }
}
