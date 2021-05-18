package model.Ordine;

import model.ConPool;
import model.Prodotto.Prodotto;
import model.Utente.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {
    public List<Ordine> doRetrieveOrders(Utente user) {
        List<Ordine> ordini = new ArrayList<>();
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM ordine WHERE id_utente = ?")) {
            ps.setInt(1, user.getId());
            ResultSet set = ps.executeQuery();

            while (set.next()) {
                Ordine order = new Ordine();
                order.setId(set.getInt("id_ordine"));
                order.setQuantita(set.getInt("quantita"));
                order.setDataOrdine(set.getDate("data_ordine"));
                order.setUser(user);
                ordini.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordini;
    }

    public Ordine doRetrieveProductsByOrder(Ordine ordine) {
        String query = "SELECT * FROM ordine AS ord INNER JOIN ordine_prodotto AS op ON ord.id_ordine = op.id_ordine " +
                "INNER JOIN prodotto AS pro ON pro.id_prodotto = op.id_prodotto WHERE ord.id_ordine = ?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);) {

            stmt.setInt(1, ordine.getId());

            ResultSet set = stmt.executeQuery();

            while (set.next()) {
                Prodotto p = new Prodotto();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
