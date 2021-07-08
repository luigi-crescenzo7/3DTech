package controller;

import model.Categoria.CategoriaDAO;
import model.Prodotto.Prodotto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/categorie/*")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = "";
        CategoriaDAO dao = new CategoriaDAO();

        switch (path) {
            case "/":
                resource = "/WEB-INF/results/categories.jsp";
                break;
            case "/category":
                String idOrder = request.getParameter("option");
                System.out.println(idOrder);
                List<Prodotto> prodotti = dao.doRetrieveProductsByCategory(Integer.parseInt(idOrder));
                request.setAttribute("products", prodotti);
                resource = "/WEB-INF/results/products.jsp";
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
