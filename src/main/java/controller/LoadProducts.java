package controller;

import model.Prodotto;
import model.ProdottoDAO;

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
        List<Prodotto> list = dao.doRetrieveAll();
        context.setAttribute("listProducts", list);
    }
}
