package controller;

import model.Prodotto;
import model.ProdottoDAO;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/ProductServlet")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productId = request.getParameter("productId");
        String fieldName = request.getParameter("fieldName");
        String fieldMaxVolume = request.getParameter("fieldMaxVolume");
        String fieldMaxSpeed = request.getParameter("fieldMaxSpeed");

        if(fieldName.isEmpty() || fieldMaxVolume.isEmpty() || fieldMaxSpeed.isEmpty())
            return;

        JSONObject obj = new JSONObject();
        obj.put("max-volume", fieldMaxVolume);
        obj.put("max-speed", fieldMaxSpeed);

        Prodotto p = new Prodotto();
        p.setId(Integer.parseInt(productId));
        p.setNome(fieldName);
        p.setMarchio("marchio88");
        p.setDescrizione("desc11");
        p.setCaratteristiche(obj);
        p.setPrezzo(99);
        p.setPeso(1);
        p.setSconto(77);

        ProdottoDAO dao = new ProdottoDAO();
        dao.doUpdateById(p);
    }
}
