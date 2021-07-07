package model.Ordine;

import model.Cart;
import model.Utente.Utente;

import java.sql.Date;
import java.time.LocalDate;

public class Ordine {
    private int id;
    private int quantita;
    private LocalDate dataOrdine;
    private int userId;
    private Cart carrello;
    private boolean visible;


    public int getUserId() {
        return userId;
    }

    public Cart getCarrello() {
        return carrello;
    }

    public void setCarrello(Cart carrello) {
        this.carrello = carrello;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean flag) {
        this.visible = flag;
    }
}
