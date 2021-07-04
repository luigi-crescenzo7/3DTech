package model.Prodotto;

import model.Categoria.Categoria;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

/*Alias tabella: pro */
public class ProdottoConstructor {

    public static Prodotto constructProduct(ResultSet rs, boolean discount) throws SQLException {
        Prodotto p = new Prodotto();
        p.setId(rs.getInt("pro.id_prodotto"));
        p.setNome(rs.getString("pro.nome"));
        p.setMarchio(rs.getString("pro.marchio"));
        p.setDescrizione(rs.getString("pro.descrizione"));
        p.setUrlImage(rs.getString("pro.image_name"));
        p.setCaratteristiche(new JSONObject(rs.getString("pro.caratteristiche")));
        p.setPrezzo(rs.getDouble((discount ? "op.prezzo_acquisto" : "pro.prezzo")));
        p.setPeso(rs.getDouble("pro.peso"));
        p.setSconto(rs.getDouble("pro.sconto"));
        Categoria cat = new Categoria();
        cat.setId(rs.getInt("cat.id_categoria"));
        cat.setNome(rs.getString("cat.nome"));
        p.setCategoria(cat);
        return p;
    }
}
