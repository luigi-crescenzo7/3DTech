package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/categorie/*")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = "";

        switch (path) {
            case "/":
                resource = "/WEB-INF/results/categories.jsp";
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
        }

        request.getRequestDispatcher(resource).forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());


        switch (path) {
            case "/create":

                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
        }


    }
}
