package model.Categoria;

import model.ConPool;
import model.Prodotto.Prodotto;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public Categoria doRetrieveCategoryProducts(Categoria categoria) {
        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM categoria AS cat" +
                     " INNER JOIN prodotto AS pro ON pro.id_categoria = cat.id_categoria" +
                     " WHERE cat.id_categoria = ?")) {
            statement.setInt(1, categoria.getId());
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                Categoria cat = new Categoria();
                cat.setId(set.getInt("cat.id_categoria"));
                cat.setNome(set.getString("cat.nome"));
                List<Prodotto> prodotti = new ArrayList<>();
                while (set.next()) {
                    Prodotto p = new Prodotto();
                    p.setId(set.getInt("pro.id_prodotto"));
                    p.setNome("pro.nome");
                    p.setMarchio("pro.marchio");
                    p.setDescrizione("pro.descrizione");
                    p.setCaratteristiche(new JSONObject(set.getString("pro.caratteristiche")));
                    p.setPrezzo(set.getDouble("pro.prezzo"));
                    p.setPeso(set.getDouble("pro.peso"));
                    p.setSconto(set.getDouble("pro.sconto"));
                    prodotti.add(p);
                }
                cat.setProdotti(prodotti);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoria;
    }

    public List<Integer> doRetrieveAllCategoriesId() {
        List<Integer> integers = new ArrayList<>();

        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id_categoria FROM categoria")) {

            ResultSet set = statement.executeQuery();

            while (set.next())
                integers.add(set.getInt("id_categoria"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return integers;
    }

    public int doRetrieveIdCategory(String category) {
        int id = 0;

        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id_categoria FROM categoria WHERE nome = ?")) {
            statement.setString(1, category);

            ResultSet set = statement.executeQuery();

            if (set.next())
                id = set.getInt("id_categoria");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}

