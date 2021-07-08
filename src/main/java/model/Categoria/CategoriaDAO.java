package model.Categoria;

import model.ConPool;
import model.Prodotto.Prodotto;
import model.Prodotto.ProdottoConstructor;
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
                cat.setUrlImage(set.getString("cat.url_image"));
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

    public List<Integer> doCountCategories() {
        List<Integer> list = new ArrayList<>();
        String query = "SELECT COUNT(pro.id_prodotto) FROM prodotto as pro JOIN categoria as cat" +
                " ON pro.id_categoria = cat.id_categoria GROUP BY pro.id_categoria";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            ResultSet set = stmt.executeQuery();

            while (set.next())
                list.add(set.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    //todo: togliere questo metodo in modo tale da far ritornare una lista di categorie (id, nome) al metodo di sopra
    public List<String> doRetrieveCategoriesName() {
        List<String> names = new ArrayList<>();
        String query = "SELECT cat.nome FROM categoria AS cat JOIN prodotto p on cat.id_categoria = p.id_categoria GROUP BY p.id_categoria";

        try (Connection connection = ConPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            ResultSet set = stmt.executeQuery();
            while (set.next())
                names.add(set.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return names;
    }

    public List<Categoria> doRetrieveAll() {
        List<Categoria> categories = new ArrayList<>();
        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM categoria AS cat")) {

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Categoria c = CategoriaConstructor.constructCategory(set);
                categories.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    public Categoria doRetrieveByName(String name) {
        Categoria categoria = null;
        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Categoria AS cat WHERE nome = ?")) {
            statement.setString(1, name);
            ResultSet set = statement.executeQuery();

            if (set.next())
                categoria = CategoriaConstructor.constructCategory(set);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoria;
    }

    public List<Prodotto> doRetrieveProductsByCategory(int idOrder) {
        String query = "SELECT *, CAST(pro.prezzo - (pro.prezzo/100) * pro.sconto AS DECIMAL(8,2)) as prezzo_scontato FROM prodotto AS pro INNER JOIN categoria" +
                " AS cat ON pro.id_categoria = cat.id_categoria WHERE cat.id_categoria = ?";
        List<Prodotto> list = null;
        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idOrder);
            list = new ArrayList<>();

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Prodotto prodotto = ProdottoConstructor.constructProduct(set, true);
                prodotto.setCategoria(CategoriaConstructor.constructCategory(set));
                list.add(prodotto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}

