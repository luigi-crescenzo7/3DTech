package model.Ordine;

import model.utilities.Cart;
import model.utilities.CartItem;
import model.Categoria.Categoria;
import model.Categoria.CategoriaConstructor;
import model.utilities.ConPool;
import model.Prodotto.Prodotto;
import model.Prodotto.ProdottoConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrdineDAO {

    public void doSave(Ordine order) {
        try (Connection connection = ConPool.getConnection()) {
            connection.setAutoCommit(false);
            String query1 = "INSERT INTO Ordine (quantita, data_ordine, id_utente) VALUES" +
                    " (?,?,?);";

            String query2 = "INSERT INTO ordine_prodotto (id_prodotto, id_ordine, quantita, prezzo_acquisto) VALUES" +
                    " (?,?,?,?);";

            try (PreparedStatement set1 = connection.prepareStatement(query1, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement set2 = connection.prepareStatement(query2)) {
                set1.setInt(1, order.getQuantita());
                set1.setDate(2, Date.valueOf(order.getDataOrdine()));
                set1.setInt(3, order.getUserId());
                int tableRows1 = set1.executeUpdate();
                ResultSet rows = set1.getGeneratedKeys();
                rows.next();
                int orderId = rows.getInt(1);
                order.setId(orderId);


                List<CartItem> list = order.getCarrello().getProdotti();
                for (CartItem item : list) {
                    set2.setInt(1, item.getProdotto().getId());
                    set2.setInt(2, order.getId());
                    set2.setInt(3, item.getQuantita());
                    set2.setDouble(4, item.getProdotto().getPrezzo());
                    if (set2.executeUpdate() != 0) {
                        System.out.println("Ok!!");
                    }
                }
                connection.commit();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ordine> doRetrieveOrdersWithProductsByUser(int idUser) {
        String query = "select *,  CAST(pro.prezzo - (pro.prezzo/100) * pro.sconto AS DECIMAL(8,2)) as prezzo_scontato" +
                " from ordine as ord inner join ordine_prodotto as op on ord.id_ordine = op.id_ordine " +
                "inner join prodotto as pro on op.id_prodotto = pro.id_prodotto inner join categoria as cat on " +
                "cat.id_categoria = pro.id_categoria where ord.id_utente = ? and ord.visibilita = 1";

        Map<Integer, Ordine> ordersMap;

        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idUser);
            ordersMap = new LinkedHashMap<>();
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                int idOrdine = set.getInt("ord.id_ordine");
                if (!ordersMap.containsKey(idOrdine)) {
                    Ordine order = OrdineConstructor.constructOrder(set);
                    order.setCarrello(new Cart(new ArrayList<>()));
                    ordersMap.put(idOrdine, order);
                }
                Prodotto prodotto = ProdottoConstructor.constructProduct(set, true);
                ordersMap.get(idOrdine).getCarrello().addProduct(prodotto, set.getInt("op.quantita"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>(ordersMap.values());
    }

    public boolean doDeleteById(int id) {
        String query = "UPDATE ordine as ord SET ord.visibilita = 0 WHERE ord.id_ordine = ?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            int result = statement.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
