package model.Categoria;

import org.json.JSONObject;

import java.util.Map;

public class CategoryBuilder {

    public static Categoria createCategory(Map<String, String[]> map, String fileName) {
        Categoria categoria = new Categoria();
        categoria.setNome(map.get("categoryName")[0]);
        categoria.setUrlImage(fileName);
        return categoria;
    }

    public static JSONObject fromObjectToJson(Categoria categoria) {
        JSONObject object = new JSONObject();
        object.put("Id", categoria.getId());
        object.put("Nome", categoria.getNome());
        object.put("Immagine", categoria.getUrlImage());
        return object;
    }
}
