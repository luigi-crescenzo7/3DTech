package controller;

import model.Prodotto.Prodotto;
import model.Prodotto.ProdottoDAO;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/ll/*")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*String productId = request.getParameter("productId");
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
        dao.doUpdateById(p);*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String resource = "/";
        String param = request.getParameter("productId");
        int id = Integer.parseInt(param);
        HttpSession session = request.getSession();
        List<Prodotto> products = null;
        ProdottoDAO dao = new ProdottoDAO();
        if (session.getAttribute("products") == null) {
            products = new ArrayList<>();
        } else {
            products = (List<Prodotto>) session.getAttribute("products");
        }

        switch (path) {
            case "/select":
                Prodotto p = dao.doRetrieveById(id);
                System.out.println(products);
                System.out.println(p);
                if (!products.contains(p)) {
                    products.add(p);
                    session.setAttribute("products", products);
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Risorsa non trovata");
                return;
        }

        request.getRequestDispatcher("/WEB-INF/results/account.jsp").forward(request, response);
    }
}
