package model.Prodotto;

import model.Categoria.Categoria;
import model.Categoria.CategoriaDAO;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class ProductBuilder {

    public static Prodotto createMaterialePlastico(Map<String, String[]> map, String fileName) {
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(map.get("productName")[0]);
        prodotto.setMarchio(map.get("productMark")[0]);
        prodotto.setDescrizione(map.get("productDescription")[0]);
        prodotto.setUrlImage(fileName);
        JSONObject object = new JSONObject();
        object.put("colore", map.get("productColor")[0]);
        object.put("temperatura", Double.parseDouble(map.get("productTemp")[0]));
        prodotto.setCaratteristiche(object);
        prodotto.setPrezzo(Double.parseDouble(map.get("productPrice")[0]));
        prodotto.setPeso(Double.parseDouble(map.get("productWeight")[0]));
        prodotto.setSconto(Double.parseDouble(map.get("productDiscount")[0]));
        return prodotto;
    }
}
