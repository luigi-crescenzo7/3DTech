package controller;

import model.utilities.Cart;
import model.utilities.CartItem;
import model.Prodotto.ProdottoDAO;
import model.utilities.RequestNotValidException;
import model.utilities.RequestValidator;

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
        String resource;

        if ("/".equals(path)) {
            RequestValidator.authenticate(session, "userSession");
            resource = "/WEB-INF/results/cart.jsp";
            request.getRequestDispatcher(resource).forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = (request.getPathInfo() == null ? " " : request.getPathInfo());
        HttpSession session = request.getSession();
        ProdottoDAO dao = new ProdottoDAO();
        Cart cart;
        String contextPath = request.getContextPath();

        if (session.getAttribute("sessionCart") == null) {
            cart = new Cart(new ArrayList<>());
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

                    if (path1 == null) {
                        response.sendRedirect(contextPath + "/");
                        return;
                    }

                    if (item != null) {
                        if (item.getProdotto().isVisible()) {
                            cart.addProduct(item.getProdotto(), Integer.parseInt(quantity));
                        }
                    } else {
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        return;
                    }

                    response.sendRedirect(contextPath + path1);
                    break;
                case "/remove":
                    //todo: forse mettere controllo se il prodotto è visibile, allora rimuoverlo, senno non fare nulla
                    RequestValidator.authenticate(session, "userSession");
                    String productId_ = request.getParameter("productId");
                    String productQuantity = request.getParameter("quantita");
                    int id1 = Integer.parseInt(productId_);
                    int quantity1 = Integer.parseInt(productQuantity);
                    if (cart.removeProduct(id1, quantity1)) {
                        request.getRequestDispatcher("/WEB-INF/results/cart.jsp").forward(request, response);
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