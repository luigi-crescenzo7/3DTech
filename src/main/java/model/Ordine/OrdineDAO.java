package model.Ordine;

import model.Cart;
import model.CartItem;
import model.Categoria.Categoria;
import model.Categoria.CategoriaConstructor;
import model.ConPool;
import model.Prodotto.Prodotto;
import model.Prodotto.ProdottoConstructor;
import model.Utente.Utente;

import java.security.cert.CertificateEncodingException;
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
                 PreparedStatement set2 = connection.prepareStatement(query2);) {
                set1.setInt(1, order.getQuantita());
                set1.setDate(2, Date.valueOf(order.getDataOrdine()));
                set1.setInt(3, order.getUserId());
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
                    set2.setDouble(4, item.getProdotto().getPrezzo());
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

    public List<Ordine> doRetrieveOrdersWithProductsByUser(int idUser) {
        String query = "select *,  CAST(pro.prezzo - (pro.prezzo/100) * pro.sconto AS DECIMAL(8,2)) as prezzo_scontato" +
                " from ordine as ord inner join ordine_prodotto as op on ord.id_ordine = op.id_ordine " +
                "inner join prodotto as pro on op.id_prodotto = pro.id_prodotto inner join categoria as cat on " +
                "cat.id_categoria = pro.id_categoria where ord.id_utente = ?";

        Map<Integer, Ordine> ordersMap = null;

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
                Categoria categoria = CategoriaConstructor.constructCategory(set);
                prodotto.setCategoria(categoria);
                ordersMap.get(idOrdine).getCarrello().addProduct(prodotto, set.getInt("op.quantita"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>(ordersMap.values());
    }

    //todo: implementazione lato dashboard sezione "ordini"
    public List<Prodotto> doRetrieveProductsByOrder(int idOrder) {
        String query = "SELECT * FROM ordine AS ord INNER JOIN ordine_prodotto AS op ON ord.id_ordine = op.id_ordine " +
                "INNER JOIN prodotto AS pro ON pro.id_prodotto = op.id_prodotto INNER JOIN categoria as cat " +
                "on pro.id_categoria = cat.id_categoria WHERE ord.id_ordine = ?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idOrder);
            ResultSet set = stmt.executeQuery();
            List<Prodotto> products = new ArrayList<>();
            while (set.next()) {
                Prodotto prodotto = ProdottoConstructor.constructProduct(set, false);
                products.add(prodotto);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
