package model;

import model.Prodotto.Prodotto;

import java.util.List;
import java.util.Optional;

public class Cart {
    private List<CartItem> prodotti;

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

    public boolean removeProduct(int id) {
        return prodotti.removeIf(item -> item.getProdotto().getId() == id);
    }

    public double getTotal() {
        double total = 0.0;
        for (CartItem item : prodotti) {
            total += item.total();
        }
        return total;
    }

    public void reset() {
        prodotti.clear();
    }
}
