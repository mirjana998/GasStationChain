package com.example.lanacbenzinskihstanica;

import java.util.Date;

public class Zaposlenik {
    protected int idZaposlenik;
    protected String jmbg;
    protected String imeZaposlenik;
    protected String prezimeZaposlenik;
    protected Date datumZaposlenja;
    protected Date datumPrestanka;
    protected String korisnickoIme;
    protected String lozinka;
    protected String adresaZaposlenik;
    protected boolean uRadnomOdnosu;
    protected int ulogaId;
    protected int mjestoId;
    protected int benzinskaId;

    public Zaposlenik(int id, String jmbg, String iz, String pz, Date dz, Date dp, String ki, String l, String a, boolean uro, int u, int m, int bs) {
        this.idZaposlenik = id;
        this.jmbg = jmbg;
        this.imeZaposlenik = iz;
        this.prezimeZaposlenik = pz;
        this.datumZaposlenja = dz;
        this.datumPrestanka = dp;
        this.korisnickoIme = ki;
        this.lozinka = l;
        this.adresaZaposlenik = a;
        this.uRadnomOdnosu = uro;
        this.ulogaId = u;
        this.mjestoId = m;
        this.benzinskaId = bs;
    }

    public Zaposlenik(Zaposlenik zaposlenik) {
        this.idZaposlenik = zaposlenik.getIdZaposlenik();
        this.jmbg = zaposlenik.getJmbg();
        this.imeZaposlenik = zaposlenik.getImeZaposlenik();
        this.prezimeZaposlenik = zaposlenik.getPrezimeZaposlenik();
        this.datumZaposlenja = zaposlenik.getDatumZaposlenja();
        this.datumPrestanka = zaposlenik.getDatumPrestanka();
        this.korisnickoIme = zaposlenik.getKorisnickoIme();
        this.lozinka = zaposlenik.getLozinka();
        this.adresaZaposlenik = zaposlenik.getAdresaZaposlenik();
        this.uRadnomOdnosu = zaposlenik.getuRadnomOdnosu();
        this.ulogaId = zaposlenik.getUlogaId();
        this.mjestoId = zaposlenik.getMjestoId();
        this.benzinskaId = zaposlenik.getBenzinskaId();
    }

    public int getIdZaposlenik() {
        return idZaposlenik;
    }

    public void setIdZaposlenik(int idZaposlenik) {
        this.idZaposlenik = idZaposlenik;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getImeZaposlenik() {
        return imeZaposlenik;
    }

    public void setImeZaposlenik(String imeZaposlenik) {
        this.imeZaposlenik = imeZaposlenik;
    }

    public String getPrezimeZaposlenik() {
        return prezimeZaposlenik;
    }

    public void setPrezimeZaposlenik(String prezimeZaposlenik) {
        this.prezimeZaposlenik = prezimeZaposlenik;
    }

    public Date getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public void setDatumZaposlenja(Date datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    public Date getDatumPrestanka() {
        return datumPrestanka;
    }

    public void setDatumPrestanka(Date datumPrestanka) {
        this.datumPrestanka = datumPrestanka;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getAdresaZaposlenik() {
        return adresaZaposlenik;
    }

    public void setAdresaZaposlenik(String adresaZaposlenik) {
        this.adresaZaposlenik = adresaZaposlenik;
    }

    public boolean getuRadnomOdnosu() {
        return uRadnomOdnosu;
    }

    public void setuRadnomOdnosu(boolean uRadnomOdnosu) {
        this.uRadnomOdnosu = uRadnomOdnosu;
    }

    public int getMjestoId() {
        return mjestoId;
    }

    public void setMjestoId(int mjestoId) {
        this.mjestoId = mjestoId;
    }

    public int getUlogaId() {
        return ulogaId;
    }

    public void setUlogaId(int ulogaId) {
        this.ulogaId = ulogaId;
    }

    public int getBenzinskaId() {
        return benzinskaId;
    }

    public void setBenzinskaId( int benzinskaId) {
        this.benzinskaId = benzinskaId;
    }
}