package controller;


import model.Categoria.Categoria;
import model.Categoria.CategoriaDAO;
import model.Categoria.CategoryBuilder;
import model.Ordine.Ordine;
import model.Prodotto.Prodotto;
import model.Prodotto.ProdottoDAO;
import model.Prodotto.ProductBuilder;
import model.Utente.UtenteDAO;
import model.utilities.RequestNotValidException;
import model.utilities.RequestValidator;
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
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/controlpanel/*")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = null;
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
                case "/categories":
                    RequestValidator.authorize(session, "userSession");
                    resource = "/WEB-INF/results/manage-categories.jsp";
                    break;
                case "/users":
                    RequestValidator.authorize(session, "userSession");
                    request.setAttribute("listUsers", new UtenteDAO().doRetrieveAll());
                    resource = "/WEB-INF/results/manage-users.jsp";
                    break;
                case "/orders":
                    RequestValidator.authorize(session, "userSession");
                    response.setContentType("application/json");
                    String userId = request.getParameter("userId");

                    List<Ordine> orders = new UtenteDAO().doRetrieveAllOrdersByUser(Integer.parseInt(userId));

                    PrintWriter writer = response.getWriter();
                    writer.println(new JSONArray(orders));
                    writer.close();
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
            }
        } catch (RequestNotValidException e) {
            e.dispatchErrors(request, response);
            return;
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(resource);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        PrintWriter writer;
        HttpSession session = request.getSession();

        switch (path) {
            case "/chart":
                RequestValidator.authorize(session, "userSession");
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
                RequestValidator.authorize(session, "userSession");
                response.setContentType("application/json");
                String id = request.getParameter("productId");

                Prodotto p = new ProdottoDAO().doRetrieveById(Integer.parseInt(id));
                JSONObject obj = ProductBuilder.fromObjectToJson(p);

                writer = response.getWriter();
                writer.println(obj);
                writer.close();
                break;
            case "/category-info":
                RequestValidator.authorize(session, "userSession");
                response.setContentType("application/json");

                String idCategoria = request.getParameter("categoryId");

                Categoria c = new CategoriaDAO().doRetrieveById(Integer.parseInt(idCategoria));
                JSONObject object = CategoryBuilder.fromObjectToJson(c);

                writer = response.getWriter();
                writer.println(object);
                writer.close();
                break;
            case "/get":
                RequestValidator.authorize(session, "userSession");
                response.setContentType("application/json");

                String productId = request.getParameter("productId");

                Prodotto item = new ProdottoDAO().doRetrieveById(Integer.parseInt(productId));
                if (item != null) {
                    writer = response.getWriter();
                    JSONObject obj3 = ProductBuilder.fromObjectToJson(item);
                    writer.println(obj3);
                    writer.close();
                }
                break;
            default:
                break;
        }
    }
}