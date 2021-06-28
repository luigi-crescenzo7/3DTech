package controller;


import model.Ordine.OrdineDAO;
import model.Utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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
                user = (Utente) session.getAttribute("userSession");
                user.setOrdini(dao.doRetrieveOrdersWithProductsByUser(user.getId()));
                request.setAttribute("userOrders", user.getOrdini());
                dispatcher = request.getRequestDispatcher("/WEB-INF/results/showOrders.jsp");
                dispatcher.forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Risorsa non trovata");
                break;
        }
    }
}
