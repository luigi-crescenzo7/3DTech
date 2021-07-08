package model.Categoria;

import java.util.Map;

public class CategoryBuilder {

    public static Categoria createCategory(Map<String, String[]> map, String fileName) {
        Categoria categoria = new Categoria();
        categoria.setNome(map.get("categoryName")[0]);
        categoria.setUrlImage(fileName);
        return categoria;
    }
}
