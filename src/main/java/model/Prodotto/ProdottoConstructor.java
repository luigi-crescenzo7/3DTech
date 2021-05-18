package model.Prodotto;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdottoConstructor {

    public static Prodotto constructProduct(ResultSet rs) throws SQLException {
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
    }
}
