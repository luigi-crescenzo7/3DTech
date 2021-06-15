package model.Prodotto;

import model.Categoria.Categoria;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdottoConstructor {
    /**
     * @param rs
     * @return
     * @throws SQLException
     */
    public static Prodotto constructProduct(ResultSet rs) throws SQLException {
        Prodotto p = new Prodotto();
        p.setId(rs.getInt("id_prodotto"));
        p.setNome(rs.getString("pro.nome"));
        p.setMarchio(rs.getString("marchio"));
        p.setDescrizione(rs.getString("descrizione"));
        p.setCaratteristiche(new JSONObject(rs.getString("caratteristiche")));
        p.setPrezzo(rs.getDouble("prezzo"));
        p.setPeso(rs.getDouble("peso"));
        p.setSconto(rs.getDouble("sconto"));
        Categoria cat = new Categoria();
        cat.setId(rs.getInt("cat.id_categoria"));
        cat.setNome(rs.getString("cat.nome"));
        p.setCategoria(cat);
        return p;
    }
}
