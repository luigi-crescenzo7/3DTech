package controller;

import model.Categoria.CategoriaDAO;
import model.Ordine.OrdineDAO;
import model.Prodotto.Prodotto;
import model.Prodotto.ProdottoDAO;
import model.Prodotto.ProductBuilder;
import model.Utente.UserSession;
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
import java.util.*;

@MultipartConfig
@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {
    private static String uploadRoot;

    @Override
    public void init() throws ServletException {
        super.init();
        uploadRoot = FileServlet.getUploadPath() + File.separator + "special_folder" + File.separator;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        ProdottoDAO dao = new ProdottoDAO();
        String resource;

        if ("/product-info".equals(path)) {
            String option = request.getParameter("option");
            int id = Integer.parseInt(option);
            Prodotto prodotto = dao.doRetrieveById(id);
            request.setAttribute("product", prodotto);
            resource = "/WEB-INF/results/productinfo.jsp";
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.getRequestDispatcher(resource).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = request.getServletContext();
        List<Prodotto> products = (List<Prodotto>) context.getAttribute("listProducts");
        System.out.println(request.getRequestURI());
        String path1 = request.getParameter("test");
        System.out.println(path1);

        try {
            String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
            HttpSession session = request.getSession();
            Prodotto p;
            ProdottoDAO dao = new ProdottoDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            switch (path) {
                case "/update":
                    RequestValidator.authorize(session, "userSession");
                    Map<String, String[]> mappa = request.getParameterMap();

                    for (Map.Entry<String, String[]> entry : mappa.entrySet()) {
                        System.out.println(entry.getKey() + " ---- " + Arrays.toString(entry.getValue()));
                    }

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

                    File file;
                    try (InputStream fileStream = part.getInputStream()) {
                        file = new File(uploadRoot + fileName);
                        if (!file.exists())
                            Files.copy(fileStream, file.toPath());
                    }

                    String resource = "/WEB-INF/results/manage-products.jsp";
                    switch (category) {
                        case "Materiale plastico":
                            System.out.println("file name: " + fileName);
                            p = ProductBuilder.createMaterialePlastico(map, fileName);
                            p.setCategoria(categoriaDAO.doRetrieveByName(map.get("productCategory")[0]));
                            dao.doSave(p);
                            if (products != null)
                                products.add(p);
                            request.getRequestDispatcher(resource).forward(request, response);
                            break;
                        case "Ricambi":
                            p = ProductBuilder.createRicambio(map, fileName);
                            p.setCategoria(categoriaDAO.doRetrieveByName(map.get("productCategory")[0]));
                            dao.doSave(p);
                            if (products != null)
                                products.add(p);
                            request.getRequestDispatcher(resource).forward(request, response);
                            break;
                        case "Accessori":
                            p = ProductBuilder.createAccessorio(map, fileName);
                            p.setCategoria(categoriaDAO.doRetrieveByName(map.get("productCategory")[0]));
                            dao.doSave(p);
                            if (products != null)
                                products.add(p);
                            request.getRequestDispatcher(resource).forward(request, response);
                            break;
                        case "Utensili":
                            p = ProductBuilder.createUtensile(map, fileName);
                            p.setCategoria(categoriaDAO.doRetrieveByName(map.get("productCategory")[0]));
                            dao.doSave(p);
                            if (products != null)
                                products.add(p);
                            request.getRequestDispatcher(resource).forward(request, response);
                            break;
                        case "Stampanti 3D":
                            p = ProductBuilder.createStampante3D(map, fileName);
                            p.setCategoria(categoriaDAO.doRetrieveByName(map.get("productCategory")[0]));
                            dao.doSave(p);
                            if (products != null)
                                products.add(p);
                            request.getRequestDispatcher(resource).forward(request, response);
                            break;
                        case "Resine":
                            p = ProductBuilder.createResina(map, fileName);
                            p.setCategoria(categoriaDAO.doRetrieveByName(map.get("productCategory")[0]));
                            dao.doSave(p);
                            if (products != null)
                                products.add(p);
                            request.getRequestDispatcher(resource).forward(request, response);
                            break;
                        default:
                            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            break;
                    }
                    break;
                //todo: da eliminare.... forse..
                case "/remove":
                    String idProdotto = request.getParameter("product-id");
                    Utente u = UserSession.getUserFromSession(session, "userSession");
                    String idOrder = request.getParameter("order-id");
                    int orderId = Integer.parseInt(idOrder);
                    int productId1 = Integer.parseInt(idProdotto);
                    System.out.println("Order id: " + orderId + " Product id: " + productId1);
                    OrdineDAO dao2 = new OrdineDAO();
                    dao2.doDeleteProductInOrder(orderId, productId1);
                    request.setAttribute("userOrders", dao2.doRetrieveOrdersWithProductsByUser(u.getId()));
                    request.getRequestDispatcher("/WEB-INF/results/orders.jsp").forward(request, response);
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