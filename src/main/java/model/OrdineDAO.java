package model;

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
}
