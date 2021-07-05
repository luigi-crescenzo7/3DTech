package controller;

import model.Prodotto.ProdottoDAO;
import org.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/search/*")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());

        switch (path) {
            case "/":
                response.setContentType("application/json");
                String parameter = request.getParameter("textContent");
                ProdottoDAO dao = new ProdottoDAO();
                List<String> productsName = dao.doRetrieveProductsByName(parameter);
                JSONArray array = new JSONArray(productsName);
                response.getWriter().println(array);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
