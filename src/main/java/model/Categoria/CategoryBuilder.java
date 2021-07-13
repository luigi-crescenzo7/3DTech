package model.Categoria;

import org.json.JSONObject;

import java.util.Map;

public class CategoryBuilder {

    public static JSONObject fromObjectToJson(Categoria categoria) {
        JSONObject object = new JSONObject();
        object.put("Id", categoria.getId());
        object.put("Nome", categoria.getNome());
        object.put("Immagine", categoria.getUrlImage());
        return object;
    }
}
