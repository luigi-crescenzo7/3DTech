package controller;

import model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;

@WebServlet(urlPatterns = "/xx/*")
public class RedirectLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        boolean flag = FieldsValidator.validateRequest(request);
        RequestDispatcher dispatcher;

        switch(path){
            case "/registration":
                if (flag) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/results/account.jsp");
                    dispatcher.forward(request, response);
                } else {

                }
                break;
            default:
                System.out.println("Error");
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
            default:
                response.sendError(404, "error");
                break;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(resource);
        dispatcher.forward(request, response);
        //response.sendRedirect(request.getContextPath() + "/WEB-INF/results/login.jsp");
    }
}
