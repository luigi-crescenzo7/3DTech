package controller;


import model.Ordine;
import model.OrdineDAO;
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



@WebServlet(urlPatterns = "/tt/*")
public class ShowOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = "/";

        /*String userId = request.getParameter("userId");

        if (userId.isEmpty()) return;

        OrdineDAO dao = new OrdineDAO();

        int id = 0;

        try {
            id = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }*/


        /*System.out.println(request.getPathInfo());
        OrdineDAO dao = new OrdineDAO();
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("user");


        user.setOrdini(dao.doRetrieveOrders(user));

        request.setAttribute("orders", user.getOrdini());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/showOrders.jsp");
        dispatcher.forward(request, response);*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        //ConPool.destroyConnection();
    }
}
