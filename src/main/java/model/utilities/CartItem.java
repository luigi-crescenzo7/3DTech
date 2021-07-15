package model.utilities;

import model.Prodotto.Prodotto;

public class CartItem {
    private Prodotto prodotto;
    private int quantita;

    public CartItem(Prodotto p, int quantity) {
        this.prodotto = p;
        this.quantita = quantity;
    }

    public double calcolaSconto() {
        return (prodotto.getPrezzo() / 100) *
                (prodotto.getSconto() > 1 && prodotto.getSconto() < 100 ? prodotto.getSconto() : 1);
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita += quantita;
    }

    public double total() {
        return (quantita * prodotto.getPrezzo());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return prodotto.equals(cartItem.prodotto);
    }

}
