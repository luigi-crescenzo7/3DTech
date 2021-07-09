package controller;

import model.utilities.Cart;
import model.utilities.CartItem;
import model.Prodotto.ProdottoDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/cart/*")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() == null ? " " : request.getPathInfo());
        HttpSession session = request.getSession();
        String resource = "/";
        String contextPath = request.getContextPath();

        switch (path) {
            case "/":
                RequestValidator.authenticate(session, "userSession");
                resource = "/WEB-INF/results/cart.jsp";
                request.getRequestDispatcher(resource).forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = (request.getPathInfo() == null ? " " : request.getPathInfo());
        HttpSession session = request.getSession();
        ProdottoDAO dao = new ProdottoDAO();
        Cart cart = null;
        String contextPath = request.getContextPath();

        if (session.getAttribute("sessionCart") == null) {
            cart = new Cart(new ArrayList<>(), 0);
            session.setAttribute("sessionCart", cart);
        } else {
            cart = (Cart) session.getAttribute("sessionCart");
        }

        try {
            switch (path) {
                case "/add":
                    RequestValidator.authenticate(session, "userSession");
                    String path1 = request.getParameter("backPath");
                    String productId = request.getParameter("productId");
                    String quantity = request.getParameter("fieldQuantity");

                    int id = Integer.parseInt(productId);
                    CartItem item = dao.doRetrieveCartItemById(id);
                    System.out.println("id:" + id + " " + (item != null));
                    if (item != null) {
                        cart.addProduct(item.getProdotto(), Integer.parseInt(quantity));
                        System.out.println("id: " + item.getProdotto().getId() + "  prezzo scontato: " + item.getProdotto().getPrezzo());
                    }
                    if (path1 != null) {
                        response.sendRedirect(contextPath + path1);
                        return;
                    }
                    response.sendRedirect(contextPath + "/");
                    break;
                case "/remove":
                    RequestValidator.authenticate(session, "userSession");
                    String productId_ = request.getParameter("productId");
                    if (cart.removeProduct(Integer.parseInt(productId_))) {
                        System.out.println("remove success");
                        request.getRequestDispatcher("/WEB-INF/results/account.jsp").forward(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (RequestNotValidException e) {
            response.sendRedirect(contextPath + "/account/login");
        }
    }
}