package com.example.lanacbenzinskihstanica;

public class StavkaFakture {
    protected int artikalId;
    protected String artikalNaziv;
    protected int fakturaId;
    protected double cijenaStavke;
    protected int kolicinaStavke;

    public StavkaFakture(int artikalId, String artikalNaziv, int fakturaId, double cijenaStavke, int kolicinaStavke) {
        this.artikalId = artikalId;
        this.artikalNaziv = artikalNaziv;
        this.fakturaId = fakturaId;
        this.cijenaStavke = cijenaStavke;
        this.kolicinaStavke = kolicinaStavke;
    }

    public StavkaFakture(StavkaFakture stavkaFakture) {
        this.artikalId = stavkaFakture.getArtikalId();
        this.artikalNaziv = stavkaFakture.getArtikalNaziv();
        this.fakturaId = stavkaFakture.getFakturaId();
        this.cijenaStavke = stavkaFakture.getCijenaStavke();
        this.kolicinaStavke = stavkaFakture.getKolicinaStavke();
    }

    public void setArtikalId(int artikalId) {
        this.artikalId = artikalId;
    }

    public int getArtikalId() {
        return artikalId;
    }

    public void setArtikalNaziv(String artikalNaziv) {
        this.artikalNaziv = artikalNaziv;
    }

    public String getArtikalNaziv() {
        return artikalNaziv;
    }

    public void setFakturaId(int fakturaId) {
        this.fakturaId = fakturaId;
    }

    public int getFakturaId() {
        return fakturaId;
    }

    public void setKolicinaStavke(int kolicinaStavke) {
        this.kolicinaStavke = kolicinaStavke;
    }

    public int getKolicinaStavke() {
        return kolicinaStavke;
    }

    public void setCijenaStavke(double cijenaStavke) {
        this.cijenaStavke = cijenaStavke;
    }

    public double getCijenaStavke() {
        return cijenaStavke;
    }
}
