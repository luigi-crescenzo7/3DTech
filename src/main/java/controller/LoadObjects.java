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

/*
 *  Questa classe svolge la funzione di caricamento di oggetti essenziali
 *  come ad esempio categorie e relativi prodotti, nell'Application Context
 * */
@WebServlet(urlPatterns = "/LoadObjects", loadOnStartup = 1)
public class LoadObjects extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Prodotto> list = prodottoDAO.doRetrieveAll();
        List<Categoria> categories = categoriaDAO.doRetrieveAll();
        context.setAttribute("listProducts", list);
        context.setAttribute("listCategories", categories);
    }
}
