package model.Ordine;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdineConstructor {

    public static Ordine constructOrder(ResultSet set) throws SQLException {
        Ordine ordine = new Ordine();
        ordine.setId(set.getInt("ord.id_ordine"));
        ordine.setUserId(set.getInt("ord.id_utente"));
        ordine.setDataOrdine(set.getDate("ord.data_ordine").toLocalDate());
        ordine.setQuantita(set.getInt("ord.quantita"));
        ordine.setVisible(set.getBoolean("ord.visibilita"));
        return ordine;
    }
}
