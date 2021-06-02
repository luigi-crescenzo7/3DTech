package model;

import model.Prodotto.Prodotto;

public class CartItem {
    private Prodotto prodotto;
    private int quantita;

    public CartItem(Prodotto p, int quantity) {
        this.prodotto = p;
        this.quantita = quantity;
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
        this.quantita = quantita;
    }

    public double total(){
        return (quantita * prodotto.getPrezzo());
    }
}
