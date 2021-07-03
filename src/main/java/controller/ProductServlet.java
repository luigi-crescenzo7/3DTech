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

import javax.servlet.ServletContext;
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
    private static String uploadRoot;

    @Override
    public void init() throws ServletException {
        super.init();
        uploadRoot = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0" + File.separator + "special_folder" + File.separator;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
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
                    CartItem item = dao.doRetrieveCartItemById(id);
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
                    RequestValidator.authorize(session, "userSession");
                    Part part = request.getPart("productImage");
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String category = request.getParameter("productCategory");
                    Map<String, String[]> map = request.getParameterMap();
                    Set<Map.Entry<String, String[]>> set = map.entrySet();
                    for (Map.Entry<String, String[]> entry : set) {
                        System.out.println(entry.getKey() + "   " + Arrays.toString(entry.getValue()));
                    }
                    System.out.println("Categoria: " + category);
                    File file = null;
                    try (InputStream fileStream = part.getInputStream()) {
                        file = new File(uploadRoot + fileName);
                        if (!file.exists())
                            Files.copy(fileStream, file.toPath());
                    }
                    switch (category) {
                        case "Materiale plastico":
                            System.out.println("file name: " + fileName);
                            Prodotto p = ProductBuilder.createMaterialePlastico(map, fileName);
                            Categoria cat = new CategoriaDAO().doRetrieveByName(map.get("productCategory")[0]);
                            p.setCategoria(cat);
                            dao.doSave(p);
                            ServletContext ctx = request.getServletContext();
                            List<Prodotto> list2 = (List<Prodotto>) ctx.getAttribute("listProducts");
                            if (list2 != null)
                                list2.add(p);
                            request.getRequestDispatcher("/WEB-INF/results/manage-products.jsp").forward(request, response);
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
                    //todo: refattorizzare in OrderServlet
                    if (products != null && products.size() > 0) {
                        Ordine order = new Ordine();
                        order.setCarrello(new Cart(products));
                        order.setQuantita(products.size());
                        order.setDataOrdine(LocalDate.now());
                        Utente user = (Utente) session.getAttribute("userSession");
                        if (user != null) {
                            order.setUserId(user.getId());
                            System.out.println("User not null");
                        }
                        OrdineDAO orderDao = new OrdineDAO();
                        orderDao.doSave(order);
                        request.getRequestDispatcher("/WEB-INF/results/account.jsp").forward(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        return;
                    }
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}