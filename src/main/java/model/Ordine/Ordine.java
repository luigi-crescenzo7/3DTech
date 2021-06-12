package model.Ordine;

import model.Cart;
import model.Utente.Utente;

import java.sql.Date;
import java.time.LocalDate;

public class Ordine {
    private int id;
    private int quantita;
    private LocalDate dataOrdine;
    private Utente user;
    private Cart carrello;
    private double total;

    public Utente getUser() {
        return user;
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

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDate dataOrdine) {
        this.dataOrdine = dataOrdine;
    }
}
