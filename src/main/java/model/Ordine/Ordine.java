package model.Ordine;

import model.Cart;
import model.Prodotto.Prodotto;
import model.Utente.Utente;

import java.sql.Date;
import java.util.List;

public class Ordine {
    private int id;
    private int quantita;
    private Date dataOrdine;
    private Utente user;
    private Cart carrello;
    private double total;

    private List<Prodotto> prodotti;

    public Utente getUser() {
        return user;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public Cart getCarrello() {
        return carrello;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setCarrello(Cart carrello) {
        this.carrello = carrello;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public void setUser(Utente user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }
}
