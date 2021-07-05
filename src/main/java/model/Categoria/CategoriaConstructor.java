package model.Categoria;

import java.sql.ResultSet;
import java.sql.SQLException;

/* Alias tabella: cat */
public class CategoriaConstructor {
    public static Categoria constructCategory(ResultSet set) throws SQLException {
        Categoria cat = new Categoria();
        cat.setId(set.getInt("cat.id_categoria"));
        cat.setNome(set.getString("cat.nome"));
        cat.setUrlImage(set.getString("cat.url_image"));
        return cat;
    }
}
