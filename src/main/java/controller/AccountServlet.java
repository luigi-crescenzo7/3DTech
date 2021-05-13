package controller;

import model.Utente;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet()
public class AccountServlet extends HttpServlet {

   /* public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = (request.getPathInfo().isEmpty() ? "/" : request.getPathInfo());
        String path = "";

        switch (url) {
            case "login":
                path = "WEB-INF/results/login.jsp";
                break;
            case "registration":
                path = "WEB-INF/results/registration.jsp";
                break;
            default:
                response.sendError(404, "error");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
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
        user.setStreet("oooooo 22");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String resource = "WEB-INF/results/login.jsp";
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("user");

        System.out.println(user.getEmail());
        System.out.println(user.isAdmin());

        if (user.isAdmin())
            resource = "/WEB-INF/results/controlpanel.jsp";


        RequestDispatcher dispatcher = request.getRequestDispatcher(resource);
        dispatcher.forward(request, response);

    }

    @Override
    public void destroy() {
        //ConPool.destroyConnection();
    }*/
}
