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


@WebServlet(urlPatterns = "/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productId = request.getParameter("productId");

        int id = 0;
        try{
            id = Integer.parseInt(productId);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }

        ProdottoDAO dao = new ProdottoDAO();
        Prodotto p = dao.doRetrieveById(id);
        request.setAttribute("product", p);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/updateProduct.jsp");
        dispatcher.forward(request, response);
    }
}
