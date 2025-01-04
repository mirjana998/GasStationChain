package com.example.lanacbenzinskihstanica;

import java.util.Date;

public class Faktura {
    protected int idFaktura;
    protected String komentar;
    protected double ukupno;
    protected Date datumIzdavanja;
    protected int zaposlenikId;
    protected int benzinskaStanicaId;
    protected int tipPlacanjaId;
    protected int racunPartneraId;
    protected int poslovniPartnerId;

    public Faktura() {
        super();
    }
    public Faktura(int idFaktura, String komentar, double ukupno, Date datumIzdavanja, int zaposlenikId, int benzinskaStanicaId, int tipPlacanjaId, int racunPartneraId, int poslovniPartnerId) {
        this.idFaktura = idFaktura;
        this.komentar = komentar;
        this.ukupno = ukupno;
        this.datumIzdavanja = datumIzdavanja;
        this.zaposlenikId = zaposlenikId;
        this.benzinskaStanicaId = benzinskaStanicaId;
        this.tipPlacanjaId = tipPlacanjaId;
        this.racunPartneraId = racunPartneraId;
        this.poslovniPartnerId = poslovniPartnerId;
    }

    public Faktura(Faktura faktura) {
        this.idFaktura = faktura.getIdFaktura();
        this.komentar = faktura.getKomentar();
        this.ukupno = faktura.getUkupno();
        this.datumIzdavanja = faktura.getDatumIzdavanja();
        this.zaposlenikId = faktura.getZaposlenikId();
        this.benzinskaStanicaId = faktura.getBenzinskaStanicaId();
        this.tipPlacanjaId = faktura.getTipPlacanjaId();
        this.racunPartneraId = faktura.getRacunPartneraId();
        this.poslovniPartnerId = faktura.getPoslovniPartnerId();
    }

    public int getIdFaktura() {
        return idFaktura;
    }

    public void setIdFaktura(int idFaktura) {
        this.idFaktura = idFaktura;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public double getUkupno() {
        return ukupno;
    }

    public void setUkupno(double ukupno) {
        this.ukupno = ukupno;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public int getZaposlenikId() {
        return zaposlenikId;
    }

    public void setZaposlenikId(int zaposlenikId) {
        this.zaposlenikId = zaposlenikId;
    }

    public int getBenzinskaStanicaId() {
        return benzinskaStanicaId;
    }

    public void setBenzinskaStanicaId(int benzinskaStanicaId) {
        this.benzinskaStanicaId = benzinskaStanicaId;
    }

    public int getRacunPartneraId() {
        return racunPartneraId;
    }

    public void setRacunPartneraId(int racunPartneraId) {
        this.racunPartneraId = racunPartneraId;
    }

    public int getPoslovniPartnerId() {
        return poslovniPartnerId;
    }

    public void setPoslovniPartnerId(int poslovniPartnerId) {
        this.poslovniPartnerId = poslovniPartnerId;
    }

    public int getTipPlacanjaId() {
        return tipPlacanjaId;
    }

    public void setTipPlacanja(int tipPlacanjaId) {
        this.tipPlacanjaId = tipPlacanjaId;
    }
}
