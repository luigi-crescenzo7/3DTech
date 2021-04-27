package controller;

import model.Utente;
import model.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fieldEmail = request.getParameter("fieldEmail");
        String fieldPassword = request.getParameter("fieldPassword");

        if (fieldPassword == null || fieldEmail == null)
            return;

        if (fieldPassword.isEmpty() || fieldEmail.isEmpty())
            return;

        Utente user = new Utente();
        UtenteDAO dao = new UtenteDAO();

        user.setEmail(fieldEmail);
        user.setPasswordhash(fieldPassword);
        user.setName("aa");
        user.setSurname("bb");
        user.setPhoneNumber("999");
        user.setZIPCode("111");
        user.setCity("aaaaooo");
        user.setStreet("oooooo 22");

        dao.doSave(user);

        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/ss.jsp").forward(request, response);
    }

    public void destroy() {

    }
}
