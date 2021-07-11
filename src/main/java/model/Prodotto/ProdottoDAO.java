package model.Prodotto;


import model.Categoria.Categoria;
import model.Categoria.CategoriaConstructor;
import model.utilities.CartItem;
import model.utilities.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {

    public boolean doUpdateById(Prodotto p) {
        int result;
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE Prodotto SET " +
                     "nome = ?, marchio = ?, descrizione = ?, image_name = ?, prezzo = ?, peso = ?, sconto = ?" +
                     " WHERE id_prodotto = ?")) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getMarchio());
            ps.setString(3, p.getDescrizione());
            ps.setString(4, p.getUrlImage());
            ps.setDouble(5, p.getPrezzo());
            ps.setDouble(6, p.getPeso());
            ps.setDouble(7, p.getSconto());
            ps.setInt(8, p.getId());

            result = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result == 1;
    }

    public boolean doDeleteById(int idProdotto) {
        int result;
        try (Connection connection = ConPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM prodotto WHERE id_prodotto = ?")) {
            stmt.setInt(1, idProdotto);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result == 1;
    }

    public List<String> doSearch(String name) {
        List<String> list = new ArrayList<>();
        String query = "SELECT nome FROM prodotto WHERE nome LIKE ?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name + "%");

            ResultSet set = statement.executeQuery();

            while (set.next())
                list.add(set.getString(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Prodotto> doRetrieveAll() {
        String sql = "SELECT * FROM prodotto AS pro INNER JOIN categoria AS cat ON pro.id_categoria = cat.id_categoria ORDER BY id_prodotto";
        List<Prodotto> list;
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet set = ps.executeQuery();
            list = new ArrayList<>();
            while (set.next()) {
                Prodotto p = ProdottoConstructor.constructProduct(set, false);
                Categoria categoria = CategoriaConstructor.constructCategory(set);
                p.setCategoria(categoria);
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public CartItem doRetrieveCartItemById(int id) {
        String sql = "SELECT *, CAST(pro.prezzo - (pro.prezzo/100) * pro.sconto AS DECIMAL(8,2)) as prezzo_scontato" +
                " FROM prodotto AS pro INNER JOIN categoria AS cat " +
                "on pro.id_categoria = cat.id_categoria WHERE pro.id_prodotto=?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                Prodotto product = ProdottoConstructor.constructProduct(set, true);
                product.setCategoria(CategoriaConstructor.constructCategory(set));
                return new CartItem(product, 1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Prodotto doRetrieveById(int id) {
        Prodotto prodotto = null;
        String query = "SELECT * FROM prodotto AS pro INNER JOIN categoria AS cat" +
                " ON pro.id_categoria = cat.id_categoria WHERE pro.id_prodotto = ?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            ResultSet set = statement.executeQuery();
            if (set.next()) {
                prodotto = ProdottoConstructor.constructProduct(set, false);
                prodotto.setCategoria(CategoriaConstructor.constructCategory(set));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prodotto;
    }

    public List<Prodotto> doRetrieveProductsByName(String name) {
        String query = "SELECT pro.*, CAST(pro.prezzo - (pro.prezzo/100) * pro.sconto AS DECIMAL(8,2)) as prezzo_scontato," +
                "cat.id_categoria, cat.nome" +
                " FROM prodotto AS pro INNER JOIN categoria AS cat on pro.id_categoria = cat.id_categoria" +
                " WHERE pro.nome LIKE ?";
        List<Prodotto> prodotti = new ArrayList<>();
        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name + "%");

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Prodotto p = ProdottoConstructor.constructProduct(set, true);
                prodotti.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prodotti;
    }

    public void doSave(Prodotto p) {
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                     "Prodotto(nome, marchio, descrizione, image_name, caratteristiche, prezzo, peso, sconto, id_categoria)" +
                     "VALUES(?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getMarchio());
            ps.setString(3, p.getDescrizione());
            ps.setString(4, p.getUrlImage());
            ps.setString(5, p.getCaratteristiche().toString());
            ps.setDouble(6, p.getPrezzo());
            ps.setDouble(7, p.getPeso());
            ps.setDouble(8, p.getSconto());
            ps.setInt(9, p.getCategoria().getId());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT error.");

            ResultSet set = ps.getGeneratedKeys();
            if (set.next())
                p.setId(set.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}