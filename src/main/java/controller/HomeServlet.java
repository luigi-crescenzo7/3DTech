package controller;

import model.Prodotto;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        String fieldUsername = request.getParameter("fieldUsername");
        String fieldPassword = request.getParameter("fieldPassword");

        if(fieldPassword == null || fieldUsername == null)
            return;

        if(fieldPassword.isEmpty() || fieldUsername.isEmpty())
            return;





    }

    public void destroy(){

    }
}
