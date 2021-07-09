package model.utilities;

import model.Prodotto.Prodotto;

import java.util.List;
import java.util.Optional;

public class Cart {
    private final List<CartItem> prodotti;
    private double total;

    public Cart(List<CartItem> list, double totale) {
        this.prodotti = list;
        this.total = totale;
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
        for (CartItem item : prodotti) {
            total += item.total();
        }
        return total;
    }

    public void setTotal(double value) {
        this.total = value;
    }

    public void reset() {
        prodotti.clear();
    }
}
