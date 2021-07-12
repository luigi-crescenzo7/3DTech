package controller;

import model.Prodotto.Prodotto;
import model.Prodotto.ProdottoDAO;
import org.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/search/*")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());

        if ("/".equals(path)) {
            response.setContentType("application/json");
            String parameter = request.getParameter("textContent");
            ProdottoDAO dao = new ProdottoDAO();
            List<String> productsName = dao.doSearch(parameter);
            JSONArray array = new JSONArray(productsName);
            response.getWriter().println(array);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = null;
        if (path.equals("/searchproduct")) {
            String option = request.getParameter("search");
            List<Prodotto> prodotti = new ProdottoDAO().doRetrieveProductsByName(option);
            request.setAttribute("products", prodotti);
            request.setAttribute("back", "/search/searchproduct?search=" + option);
            request.getRequestDispatcher("/WEB-INF/results/products.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
