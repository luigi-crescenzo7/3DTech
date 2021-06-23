package controller;

import model.Categoria.Categoria;
import model.Categoria.CategoriaDAO;
import model.Prodotto.Prodotto;
import model.Prodotto.ProdottoDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.List;

@WebServlet(urlPatterns = "/LoadProducts", loadOnStartup = 1)
public class LoadProducts extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        ProdottoDAO dao = new ProdottoDAO();
        CategoriaDAO catDao = new CategoriaDAO();
        List<Prodotto> list = dao.doRetrieveAll();
        List<Categoria> categories = catDao.doRetrieveAll();
        context.setAttribute("listProducts", list);
        context.setAttribute("listCategories", categories);
    }
}
