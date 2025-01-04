package com.example.lanacbenzinskihstanica;

public class Mjesto {
    protected int idMjesta;
    protected String brojPoste;
    protected String nazivMjesta;

    public Mjesto() {
        super();
    }

    public Mjesto(int id, String bp, String nm) {
        idMjesta = id;
        brojPoste = bp;
        nazivMjesta = nm;
    }

    public Mjesto(Mjesto m) {
        this.idMjesta = m.getIdMjesta();
        this.brojPoste = m.getBrojPoste();
        this.nazivMjesta = m.getNazivMjesta();
    }

    public void setIdMjesta(int id) {
        this.idMjesta = id;
    }

    public int getIdMjesta() {
        return idMjesta;
    }

    public void setBrojPoste(String bp) {
        this.brojPoste = bp;
    }

    public String getBrojPoste() {
        return brojPoste;
    }

    public void setNazivMjesta(String nm) {
        this.nazivMjesta = nm;
    }

    public String getNazivMjesta() {
        return nazivMjesta;
    }
}