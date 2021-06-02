package model;

import model.Prodotto.Prodotto;

import java.util.List;

public class Cart {
    private List<CartItem> prodotti;

    public Cart(List<CartItem> list) {
        this.prodotti = list;
    }

    public List<CartItem> getProdotti() {
        return prodotti;
    }

    public void addProduct(Prodotto p, int quantita){
        prodotti.add(new CartItem(p, quantita));
    }

    public double getTotal() {
        double total = 0.0;
        for (CartItem item : prodotti) {
            total += item.total();
        }
        return total;
    }
}
