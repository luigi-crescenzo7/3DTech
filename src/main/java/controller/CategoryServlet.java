package controller;


import model.Categoria.CategoriaDAO;
import model.Prodotto.Prodotto;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/categorie/*")
@MultipartConfig
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource;
        CategoriaDAO dao = new CategoriaDAO();

        switch (path) {
            case "/":
                resource = "/WEB-INF/results/categories.jsp";
                break;
            case "/category":
                String idCategory = request.getParameter("option");
                if (!Pattern.compile("^(?=.*[^\\s])\\d*$").matcher(idCategory).matches()) {
                    resource = "/index.jsp";
                    request.setAttribute("errorMessage", "Categoria inesistente");
                    request.getRequestDispatcher(resource).forward(request, response);
                    return;
                }

                List<Prodotto> prodotti = dao.doRetrieveProductsByCategory(Integer.parseInt(idCategory));
                request.setAttribute("products", prodotti);
                request.setAttribute("back", "/categorie/category?option=" + idCategory);
                resource = "/WEB-INF/results/products.jsp";
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
        }
        request.getRequestDispatcher(resource).forward(request, response);
    }
}