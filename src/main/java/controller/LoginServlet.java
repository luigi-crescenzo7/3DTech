package controller;

import model.Utente;
import model.UtenteDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

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

        user = dao.doRetrieveEmailPassword(user);

        if (user == null) return;

        HttpSession session = request.getSession();

        session.setAttribute("user", user);

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        doGet(request, response);
    }

    @Override
    public void destroy() {
        //ConPool.destroyConnection();
    }
}
