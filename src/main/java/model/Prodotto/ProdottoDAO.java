package model.Prodotto;


import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {

    /*public void doSaveJson(String s) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO test VALUE(?)")) {
            ps.setString(1, s);
            if (ps.executeUpdate() != 1) throw new RuntimeException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String doRetrieveJson() {
        String s = "";
        try (PreparedStatement ps = connection.prepareStatement("SELECT * from test WHERE json_column->'$.name' = ?")) {
            ps.setString(1, "gigi");
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                s = set.getString("json_column");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }*/

    public boolean doUpdateById(Prodotto p) {
        int result;
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE Prodotto SET " +
                     "nome = ?, marchio = ?, descrizione = ?, image_name = ?, caratteristiche = ?, prezzo = ?, peso = ?, sconto = ?" +
                     " WHERE id_prodotto = ?")) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getMarchio());
            ps.setString(3, p.getDescrizione());
            ps.setString(4, p.getUrlImage());
            ps.setObject(5, p.getCaratteristiche().toString());
            ps.setDouble(6, p.getPrezzo());
            ps.setDouble(7, p.getPeso());
            ps.setDouble(8, p.getSconto());
            ps.setInt(9, p.getId());

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

    /*
    public List<Prodotto> doSearch(List<Condition> conditions) {
        List<Prodotto> products = new ArrayList<>();
        String query = "SELECT * FROM prodotto AS pro INNER JOIN categoria AS cat" +
                " ON pro.id_categoria = cat.id_categoria WHERE " + SqlJoiner.queryJoiner(conditions, "pro");
        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < conditions.size(); i++) {
                Condition condition = conditions.get(i);
                if (condition.getOperator() == Operator.LIKE) {
                    statement.setObject(i + 1, "%" + condition.getValue() + "%");
                } else {
                    statement.setObject(i + 1, condition.getValue());
                }
            }

            ResultSet set = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }*/

    public boolean doDeleteAll() {
        int result;
        try (Connection connection = ConPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM prodotto;")) {
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result > 0;
    }

    public List<Prodotto> doRetrieveAll() {
        String sql = "SELECT * FROM prodotto AS pro INNER JOIN categoria AS cat ON pro.id_categoria = cat.id_categoria ORDER BY id_prodotto";
        List<Prodotto> list;
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                Prodotto p = ProdottoConstructor.constructProduct(rs);
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public CartItem doRetrieveById(int id) {
        String sql = "SELECT * FROM Prodotto as pro WHERE id_prodotto=?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Prodotto product = ProdottoConstructor.constructProduct(rs);
                return new CartItem(product, 1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<String> doRetrieveProductsByName(String name) {
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
    //TODO forse da eliminare
    /*public String doRetrieveCaratteristiche(int id) {
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prodotto" +
                     " WHERE id_prodotto = ?")) {
            ps.setInt(1, id);

            ResultSet set = ps.executeQuery();
            if (set.next()) {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "";
    }*/
}