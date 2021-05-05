package controller;

import model.*;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        /*ProdottoDAO dao1 = new ProdottoDAO();

        JSONObject obj1 = new JSONObject();
        obj1.put("name", "gigi");
        obj1.put("surname", "kitammuò");
        obj1.put("phoneNumber", 9889);

        dao1.doSaveJson(obj1.toString());

        try{
            JSONObject obj = new JSONObject(dao1.doRetrieveJson());
            String s = (String) obj.get("name");
            System.out.println(s);
        }catch(JSONException e){
            e.printStackTrace();
        }

        user.setEmail(fieldEmail);
        user.setPasswordhash(fieldPassword);
        user.setName("aa");
        user.setSurname("bb");
        user.setPhoneNumber("999");
        user.setZIPCode("111");
        user.setCity("aaaaooo");
        user.setStreet("oooooo 22");*/


        request.getRequestDispatcher("/WEB-INF/ss.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        ConPool.destroyConnection();
    }
}
