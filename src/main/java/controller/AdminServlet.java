package controller;


import model.Categoria.CategoriaDAO;
import model.Prodotto.Prodotto;
import model.Prodotto.ProdottoDAO;
import model.Prodotto.ProductBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

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
        String resource = "";
        HttpSession session = request.getSession();

        try {
            switch (path) {
                case "/":
                    RequestValidator.authorize(session, "userSession");
                    resource = "/WEB-INF/results/admin-dashboard.jsp";
                    break;
                case "/products":
                    RequestValidator.authorize(session, "userSession");
                    resource = "/WEB-INF/results/manage-products.jsp";
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
            }
        } catch (RequestNotValidException e) {
            response.sendRedirect(request.getContextPath() + "/account/admin");
            return;
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(resource);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        System.out.println("doPost " + path);
        PrintWriter writer = null;

        switch (path) {
            case "/chart":
                response.setContentType("application/json");

                JSONArray array = new JSONArray();
                CategoriaDAO dao = new CategoriaDAO();
                List<Integer> nums = dao.doCountCategories();
                List<String> names = dao.doRetrieveCategoriesName();

                array.put(0, names);
                array.put(1, nums);

                writer = response.getWriter();
                writer.println(array);
                writer.close();
                break;
            case "/product-info":
                response.setContentType("application/json");
                String id = request.getParameter("productId");

                Prodotto p = new ProdottoDAO().doRetrieveById(Integer.parseInt(id));
                JSONObject obj = ProductBuilder.fromObjectToJson(p);

                writer = response.getWriter();
                writer.println(obj);
                writer.close();
                break;
            default:
                break;
        }
    }
}
