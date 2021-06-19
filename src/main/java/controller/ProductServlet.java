package controller;

import model.Cart;
import model.CartItem;
import model.Categoria.Categoria;
import model.Categoria.CategoriaDAO;
import model.Ordine.Ordine;
import model.Ordine.OrdineDAO;
import model.Prodotto.Prodotto;
import model.Prodotto.ProdottoDAO;
import model.Prodotto.ProductBuilder;
import model.Utente.Utente;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@MultipartConfig
@WebServlet(urlPatterns = "/ll/*")
public class ProductServlet extends HttpServlet {
    private static String uploadRoot = System.getenv("CATALINA_HOME") + File.separator + "special_folder" + File.separator;

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
        String quantity = request.getParameter("fieldQuantity");
        HttpSession session = request.getSession();
        List<CartItem> products = null;
        ProdottoDAO dao = new ProdottoDAO();
        if (session.getAttribute("products") == null) {
            products = new ArrayList<>();
        } else {
            products = (List<CartItem>) session.getAttribute("products");
        }

        switch (path) {
            case "/select":
                int id = Integer.parseInt(param);
                CartItem item = dao.doRetrieveById(id);
                // Se il prodotto non è nella lista dei prodotti della sessione allora lo aggiungo
                if (!products.contains(item)) {
                    if (quantity != null && !quantity.isBlank())
                        item.setQuantita(Integer.parseInt(quantity));
                    products.add(item);
                    session.setAttribute("products", products);
                } else { // Se invece è presente ed è definita la quantità, allora vecchiaQuantità + nuovaQuantità
                    int pos = products.indexOf(item);
                    item = products.get(pos);

                    if (quantity != null && !quantity.isBlank())
                        item.setQuantita(item.getQuantita() + Integer.parseInt(quantity));
                    else
                        item.setQuantita(item.getQuantita() + 1);
                }
                request.getRequestDispatcher("/WEB-INF/results/account.jsp").forward(request, response);
                break;
            case "/create":
                Part part = request.getPart("productImage");
                String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                List<String> list = FormExtractor.retrieveParameterValues(request);
                String category = request.getParameter("productCategory");
                //List<String> parameters = FormExtractor.retrieveParameterValues(request);
                Map<String, String[]> map = request.getParameterMap();
                Set<Map.Entry<String, String[]>> set = map.entrySet();
                for (Map.Entry<String, String[]> entry : set) {
                    System.out.println(entry.getKey() + "   " + Arrays.toString(entry.getValue()));
                }
                System.out.println("Categoria: " + category);

                try (InputStream fileStream = part.getInputStream()) {
                    File file = new File(uploadRoot + fileName);
                    if (!file.exists())
                        Files.copy(fileStream, file.toPath());
                } catch (IOException e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    return;
                }
                switch (category) {
                    case "Materiale plastico":
                        System.out.println("file name: " + fileName);
                        Prodotto p = new Prodotto();
                        p.setNome(map.get("productName")[0]);
                        p.setMarchio(map.get("productMark")[0]);
                        p.setDescrizione(map.get("productDescription")[0]);
                        p.setUrlImage(fileName);
                        //p.setCaratteristiche(ProductBuilder.createMaterialePlastico(parameters));
                        p.setCaratteristiche(new JSONObject("{\"ciao\":1}"));
                        p.setPrezzo(1);
                        p.setPeso(2);
                        p.setSconto(3);
                        Categoria cat = new Categoria();
                        cat.setId(new CategoriaDAO().doRetrieveIdCategory(category));
                        System.out.println(cat.getId());
                        cat.setNome(category);
                        p.setCategoria(cat);
                        dao.doSave(p);
                        request.setAttribute("createdProduct", p);
                        request.getRequestDispatcher("/WEB-INF/results/productinfo.jsp").forward(request, response);
                        break;
                    case "thrgh":
                        break;
                    case "Stampanti 3D":
                        System.out.println("Case: Stampanti 3D");
                        break;
                    default:
                        System.out.println("Default case");
                        break;
                }
                break;
            case "/checkout":
                if (products != null && products.size() > 0) {
                    Ordine order = new Ordine();
                    order.setCarrello(new Cart(products));
                    order.setQuantita(products.size());
                    order.setDataOrdine(LocalDate.now());
                    order.setUser((Utente) session.getAttribute("user"));
                    OrdineDAO orderDao = new OrdineDAO();
                    orderDao.doSave(order);
                    request.getRequestDispatcher("/WEB-INF/results/account.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}