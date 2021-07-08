package model.Prodotto;

import model.Categoria.Categoria;
import model.Categoria.CategoriaConstructor;
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
        p.setPrezzo(rs.getDouble((discount ? "prezzo_scontato" : "pro.prezzo")));
        p.setPeso(rs.getDouble("pro.peso"));
        p.setSconto(rs.getDouble("pro.sconto"));
        //p.setVisible(rs.getBoolean("pro.visibilita"));
        Categoria cat = CategoriaConstructor.constructCategory(rs);
        p.setCategoria(cat);
        return p;
    }
}
