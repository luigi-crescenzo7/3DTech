package controller;


import model.Categoria.CategoriaDAO;
import model.Utente.Utente;
import org.json.JSONArray;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/controlpanel/*")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource;
        HttpSession session = request.getSession();
        Utente administrator = (Utente) session.getAttribute("administrator");
        Utente user = (Utente) session.getAttribute("user");

        switch (path) {
            case "/":
                if (administrator != null || user.isAdmin()) {
                    resource = "/WEB-INF/results/admin-dashboard.jsp";
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(resource);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = "/";
        System.out.println("doPost "+path);


        switch (path) {
            case "/chart":
                response.setContentType("text/json");

                JSONArray array = new JSONArray();
                CategoriaDAO dao = new CategoriaDAO();
                List<Integer> nums = dao.doCountCategories();
                List<String> names = dao.doRetrieveCategoriesName();

                array.put(0, names);
                array.put(1, nums);

                PrintWriter writer = response.getWriter();
                writer.println(array);
                writer.close();
                break;
            default:
                break;
        }
    }
}
