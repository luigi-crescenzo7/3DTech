package controller;


import model.ConPool;
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

@WebServlet(urlPatterns = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*String fieldEmail = request.getParameter("fieldEmail");
        String fieldPassword = request.getParameter("fieldPassword");
        String fieldName = request.getParameter("fieldName");
        String fieldSurname = request.getParameter("fieldSurname");
        String fieldPhoneNumber = request.getParameter("fieldPhoneNumber");
        String fieldZIPCode = request.getParameter("fieldZIPCode");
        String fieldCity = request.getParameter("fieldCity");
        String fieldStreet = request.getParameter("fieldStreet");*/

        UtenteDAO dao = new UtenteDAO();

        /*Utente user = new Utente();
        user.setEmail(fieldEmail);
        user.setPasswordhash(fieldPassword);
        user.setName(fieldName);
        user.setSurname(fieldSurname);
        user.setPhoneNumber(fieldPhoneNumber);
        user.setZIPCode(fieldZIPCode);
        user.setCity(fieldCity);
        user.setStreet(fieldStreet);

        dao.doSave(user);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/account.jsp");
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
