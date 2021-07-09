package controller;

import model.Categoria.Categoria;
import model.Categoria.CategoriaDAO;
import model.Categoria.CategoryBuilder;
import model.Prodotto.Prodotto;

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
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/categorie/*")
@MultipartConfig
public class CategoryServlet extends HttpServlet {
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
        String resource;
        CategoriaDAO dao = new CategoriaDAO();

        switch (path) {
            case "/":
                resource = "/WEB-INF/results/categories.jsp";
                break;
            case "/category":
                String idCategory = request.getParameter("option");
                System.out.println(idCategory);
                List<Prodotto> prodotti = dao.doRetrieveProductsByCategory(Integer.parseInt(idCategory));
                request.setAttribute("products", prodotti);
                request.setAttribute("back", "/categorie/category?option=" + idCategory);
                resource = "/WEB-INF/results/products.jsp";
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
        }
        request.getRequestDispatcher(resource).forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        CategoriaDAO dao = new CategoriaDAO();
        List<Categoria> categorie;

        if ("/create".equals(path)) {
            RequestValidator.authorize(session, "userSession");
            Part part = request.getPart("categoryImage");
            Map<String, String[]> map = request.getParameterMap();
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            File file;
            try (InputStream fileStream = part.getInputStream()) {
                file = new File(uploadRoot + fileName);
                if (!file.exists())
                    Files.copy(fileStream, file.toPath());
            }
            Categoria categoria = CategoryBuilder.createCategory(map, fileName);
            categorie = (List<Categoria>) context.getAttribute("listCategories");
            dao.doSave(categoria);
            if (categorie != null)
                categorie.add(categoria);
            request.getRequestDispatcher("/WEB-INF/results/admin-dashboard.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
