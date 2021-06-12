package model.Prodotto;

import model.Categoria.Categoria;
import org.json.JSONObject;

public class Prodotto {
    private int id;
    private String nome;
    private String descrizione;
    private String marchio;
    private JSONObject caratteristiche;
    private double prezzo;
    private double peso;
    private double sconto;
    private Categoria categoria;


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public JSONObject getCaratteristiche() {
        return caratteristiche;
    }

    public void setCaratteristiche(JSONObject caratteristiche) {
        this.caratteristiche = caratteristiche;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getMarchio() {
        return marchio;
    }

    public void setMarchio(String marchio) {
        this.marchio = marchio;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getSconto() {
        return sconto;
    }

    public void setSconto(double sconto) {
        this.sconto = sconto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return id == prodotto.id;
    }

    public String toString(){
        return "Prodotto{id: "+id+"}";
    }
}
