package model.Prodotto;

import org.json.JSONObject;

import java.util.List;

public class ProductBuilder {

    public static JSONObject createMaterialePlastico(List<String> parameters) {
        JSONObject object = new JSONObject();
        object.put("colore", parameters.get(0));
        object.put("temperatura", Double.parseDouble(parameters.get(1)));
        return object;
    }
}
