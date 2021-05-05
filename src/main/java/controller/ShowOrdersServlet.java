package controller;

import model.ConPool;
import model.Ordine;
import model.Utente;
import model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/ShowOrders")
public class ShowOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");

        if (userId.isEmpty() || userId == null) return;

        UtenteDAO dao = new UtenteDAO();
        Utente user;

        int id = 0;

        try {
            id = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        user = (Utente) session.getAttribute("user");

        System.out.println(id);

        user.setOrdini(dao.doRetrieveOrders(id));

        request.setAttribute("orders", user.getOrdini());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/showOrders.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        ConPool.destroyConnection();
    }
}
