package model.utilities;

import model.Prodotto.Prodotto;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Cart {
    private final List<CartItem> prodotti;

    public Cart(List<CartItem> list) {
        this.prodotti = list;
    }

    public List<CartItem> getProdotti() {
        return prodotti;
    }

    public void addProduct(Prodotto p, int quantita) {
        Optional<CartItem> item = find(p.getId());
        if (item.isPresent()) {
            item.get().setQuantita(quantita);
        } else {
            prodotti.add(new CartItem(p, quantita));
        }
    }

    public int totaleProdotti() {
        return prodotti.stream().mapToInt(CartItem::getQuantita).reduce(0, Integer::sum);
    }

    public Optional<CartItem> find(int id) {
        return prodotti.stream().filter(item -> item.getProdotto().getId() == id).findFirst();
    }

    public boolean removeProduct(int id, int quantita) {
        Optional<CartItem> item1 = find(id);
        if (item1.isPresent()) {
            if (quantita >= 1 && quantita < item1.get().getQuantita()) {
                item1.get().setQuantita(-(quantita));
                return true;
            }
        }
        return prodotti.removeIf(item -> item.getProdotto().getId() == id);
    }

    public double getTotal() {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getNumberInstance(Locale.getDefault());
        formatter.applyPattern("0.00");
        double total = 0;
        for (CartItem item : prodotti) {
            total += item.total();
        }
        return Double.parseDouble(formatter.format(total).replace(',', '.'));
    }


    public void reset() {
        prodotti.clear();
    }
}
