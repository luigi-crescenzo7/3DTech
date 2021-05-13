package model;


import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void doUpdateById(Prodotto p) {
        try (Connection connection = ConPool.getConnection();
                PreparedStatement ps = connection.prepareStatement("UPDATE prodotto" +
                " SET nome = ?, marchio = ?, descrizione = ?, caratteristiche = ?," +
                "prezzo = ?, peso = ?, sconto = ? WHERE id_prodotto = ?")) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getMarchio());
            ps.setString(3, p.getDescrizione());
            ps.setString(4, p.getCaratteristiche().toString());
            ps.setDouble(5, p.getPrezzo());
            ps.setDouble(6, p.getPeso());
            ps.setDouble(7, p.getSconto());
            ps.setInt(8, p.getId());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetrieveAll() {
        String sql = "SELECT * FROM Prodotto";
        try (
                Connection connection = ConPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> array = new ArrayList<>();
            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt("id_prodotto"));
                p.setNome(rs.getString("nome"));
                p.setMarchio(rs.getString("marchio"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setCaratteristiche(new JSONObject(rs.getString("caratteristiche")));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setPeso(rs.getDouble("peso"));
                p.setSconto(rs.getDouble("sconto"));
                array.add(p);
            }
            return array;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Prodotto doRetrieveById(int id) {

        String sql = "SELECT * FROM Prodotto WHERE id_prodotto=?";
        try (Connection connection = ConPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            rs.next();

            Prodotto p = new Prodotto();

            p.setId(rs.getInt("id_prodotto"));
            p.setNome(rs.getString("nome"));
            p.setMarchio(rs.getString("marchio"));
            p.setDescrizione(rs.getString("descrizione"));
            p.setCaratteristiche(new JSONObject(rs.getString("caratteristiche")));
            p.setPrezzo(rs.getDouble("prezzo"));
            p.setPeso(rs.getDouble("peso"));
            p.setSconto(rs.getDouble("sconto"));
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Prodotto p) {
        try (Connection connection = ConPool.getConnection();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                "Prodotto(nome, marchio, descrizione, caratteristiche, prezzo, peso, sconto, id_categoria)" +
                "VALUES(?,?,?,?,?,?,?,?)")) {
            ps.setInt(1, p.getId());
            ps.setString(2, p.getNome());
            ps.setString(3, p.getMarchio());
            ps.setString(4, p.getDescrizione());
            ps.setDouble(5, p.getPrezzo());
            ps.setDouble(6, p.getPeso());
            ps.setDouble(7, p.getSconto());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateProdottobyId(int id, Prodotto p) {
        try (Connection connection = ConPool.getConnection();
                PreparedStatement ps = connection.prepareStatement("UPDATE Prodotto SET " +
                "nome=?, marchio=?, descrizione=?, prezzo=?, peso=?, sconto=?" +
                " WHERE id_prodotto = ?")) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getMarchio());
            ps.setString(3, p.getDescrizione());
            ps.setDouble(4, p.getPrezzo());
            ps.setDouble(5, p.getPeso());
            ps.setDouble(6, p.getSconto());
            ps.setInt(7, id);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //TODO implementare metodo
    public String doRetrieveCaratteristiche(int id) {
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
    }
}