package model.Ordine;

import model.CartItem;
import model.ConPool;
import model.Prodotto.Prodotto;
import model.Utente.Utente;

import java.sql.*;
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
                order.setDataOrdine(set.getDate("data_ordine").toLocalDate());
                order.setUser(user);
                ordini.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordini;
    }

    public void doSave(Ordine order) {
        try (Connection connection = ConPool.getConnection()) {
            connection.setAutoCommit(false);
            String query1 = "INSERT INTO Ordine (quantita, data_ordine, id_utente) VALUES" +
                    " (?,?,?);";

            String query2 = "INSERT INTO ordine_prodotto (id_prodotto, id_ordine, quantita) VALUES" +
                    " (?,?,?);";

            try (PreparedStatement set1 = connection.prepareStatement(query1, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement set2 = connection.prepareStatement(query2);) {
                set1.setInt(1, order.getQuantita());
                set1.setDate(2, Date.valueOf(order.getDataOrdine()));
                set1.setInt(3, order.getUser().getId());
                int tableRows1 = set1.executeUpdate();
                System.out.println("Table rows: " + tableRows1);
                ResultSet rows = set1.getGeneratedKeys();
                rows.next();
                int orderId = rows.getInt(1);
                order.setId(orderId);

                int total = tableRows1;
                List<CartItem> list = order.getCarrello().getProdotti();
                for (CartItem item : list) {
                    set2.setInt(1, item.getProdotto().getId());
                    set2.setInt(2, order.getId());
                    set2.setInt(3, item.getQuantita());
                    total += set2.executeUpdate();
                }
                System.out.println("Total: " + total);
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
