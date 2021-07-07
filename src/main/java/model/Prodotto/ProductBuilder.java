package model.Prodotto;

import org.json.JSONObject;

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

    public static Prodotto createStampante3D(Map<String, String[]> map, String fileName) {
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(map.get("productName")[0]);
        prodotto.setMarchio(map.get("productMark")[0]);
        prodotto.setDescrizione(map.get("productDescription")[0]);
        prodotto.setUrlImage(fileName);
        prodotto.setCaratteristiche(new JSONObject());
        prodotto.setPrezzo(Double.parseDouble(map.get("productPrice")[0]));
        prodotto.setPeso(Double.parseDouble(map.get("productWeight")[0]));
        prodotto.setSconto(Double.parseDouble(map.get("productDiscount")[0]));
        return prodotto;
    }

    public static Prodotto createRicambio(Map<String, String[]> map, String fileName) {
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(map.get("productName")[0]);
        prodotto.setMarchio(map.get("productMark")[0]);
        prodotto.setDescrizione(map.get("productDescription")[0]);
        prodotto.setUrlImage(fileName);
        prodotto.setCaratteristiche(new JSONObject());
        prodotto.setPrezzo(Double.parseDouble(map.get("productPrice")[0]));
        prodotto.setPeso(Double.parseDouble(map.get("productWeight")[0]));
        prodotto.setSconto(Double.parseDouble(map.get("productDiscount")[0]));
        return prodotto;
    }

    public static Prodotto createAccessorio(Map<String, String[]> map, String fileName) {
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(map.get("productName")[0]);
        prodotto.setMarchio(map.get("productMark")[0]);
        prodotto.setDescrizione(map.get("productDescription")[0]);
        prodotto.setUrlImage(fileName);
        prodotto.setCaratteristiche(new JSONObject());
        prodotto.setPrezzo(Double.parseDouble(map.get("productPrice")[0]));
        prodotto.setPeso(Double.parseDouble(map.get("productWeight")[0]));
        prodotto.setSconto(Double.parseDouble(map.get("productDiscount")[0]));
        return prodotto;

    }

    public static Prodotto createUtensile(Map<String, String[]> map, String fileName) {
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(map.get("productName")[0]);
        prodotto.setMarchio(map.get("productMark")[0]);
        prodotto.setDescrizione(map.get("productDescription")[0]);
        prodotto.setUrlImage(fileName);
        prodotto.setCaratteristiche(new JSONObject());
        prodotto.setPrezzo(Double.parseDouble(map.get("productPrice")[0]));
        prodotto.setPeso(Double.parseDouble(map.get("productWeight")[0]));
        prodotto.setSconto(Double.parseDouble(map.get("productDiscount")[0]));
        return prodotto;
    }

    public static Prodotto createResina(Map<String, String[]> map, String fileName) {
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(map.get("productName")[0]);
        prodotto.setMarchio(map.get("productMark")[0]);
        prodotto.setDescrizione(map.get("productDescription")[0]);
        prodotto.setUrlImage(fileName);
        prodotto.setCaratteristiche(new JSONObject());
        prodotto.setPrezzo(Double.parseDouble(map.get("productPrice")[0]));
        prodotto.setPeso(Double.parseDouble(map.get("productWeight")[0]));
        prodotto.setSconto(Double.parseDouble(map.get("productDiscount")[0]));
        return prodotto;
    }

    public static JSONObject fromObjectToJson(Prodotto p) {
        JSONObject object = new JSONObject();
        object.put("id", p.getId());
        object.put("nome", p.getNome());
        object.put("descrizione", p.getDescrizione());
        object.put("marchio", p.getMarchio());
        object.put("immagine", p.getUrlImage());
        object.put("caratteristiche", p.getCaratteristiche());
        object.put("prezzo", p.getPrezzo());
        object.put("peso", p.getPeso());
        object.put("sconto", p.getSconto());
        object.put("categoria", p.getCategoria().getNome());
        return object;
    }
}
