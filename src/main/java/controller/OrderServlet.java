package controller;


import model.Cart;
import model.CartItem;
import model.Ordine.Ordine;
import model.Ordine.OrdineDAO;
import model.Prodotto.Prodotto;
import model.Utente.UserSession;
import model.Utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/tt/*")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = "/";
        OrdineDAO dao = new OrdineDAO();
        HttpSession session = request.getSession();
        Utente user;
        RequestDispatcher dispatcher;

        switch (path) {
            case "/orders":
                RequestValidator.authenticate(session, "userSession");
                user = UserSession.getUserFromSession(session, "userSession");
                user.setOrdini(dao.doRetrieveOrdersWithProductsByUser(user.getId()));
                request.setAttribute("userOrders", user.getOrdini());
                request.getRequestDispatcher("/WEB-INF/results/orders.jsp").forward(request, response);
                break;
            case "/checkout":
                RequestValidator.authenticate(session, "userSession");
                user = UserSession.getUserFromSession(session, "userSession");
                List<CartItem> products = (List<CartItem>) session.getAttribute("products");
                if (products != null && products.size() > 0) {
                    Ordine order = new Ordine();
                    order.setCarrello(new Cart(products));
                    order.setQuantita(products.size());
                    order.setDataOrdine(LocalDate.now());
                    order.setUserId(user.getId());
                    Cart c = order.getCarrello();
                    OrdineDAO orderDao = new OrdineDAO();
                    orderDao.doSave(order);
                    c.reset();
                    request.getRequestDispatcher("/WEB-INF/results/account.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Carrello vuoto");
                    return;
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Risorsa non trovata");
                break;
        }
    }
}
