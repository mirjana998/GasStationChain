package com.example.lanacbenzinskihstanica;

public class Racun {
    protected int idRacun;
    protected int bankaId;
    protected String brojRacuna;

    public Racun() {
        super();
    }

    public Racun(int idRacun, int bankaId, String brojRacuna)
    {
        this.idRacun = idRacun;
        this.bankaId = bankaId;
        this.brojRacuna = brojRacuna;
    }

    public void setIdRacun(int idRacun) {
        this.idRacun = idRacun;
    }

    public int getIdRacun() {
        return idRacun;
    }

    public void setBankaId(int bankaId) {
        this.bankaId = bankaId;
    }

    public int getBankaId() {
        return bankaId;
    }

    public void setBrojRacuna(String brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public String getBrojRacuna() {
        return brojRacuna;
    }
}