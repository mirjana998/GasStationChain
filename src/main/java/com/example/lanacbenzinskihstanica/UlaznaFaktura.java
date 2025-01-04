package com.example.lanacbenzinskihstanica;

import java.util.Date;

public class UlaznaFaktura extends Faktura {
    protected Date datumIsporuke;

    public UlaznaFaktura(int idFaktura, String komentar, double ukupno, Date datumIzdavanja, int zaposlenikId, int benzinskaStanicaId, int tipPlacanjaId, int racunPartneraId, int poslovniPartnerId, Date datumIsporuke) {
        super(idFaktura, komentar, ukupno, datumIzdavanja, zaposlenikId, benzinskaStanicaId, tipPlacanjaId, racunPartneraId, poslovniPartnerId);
        this.datumIsporuke = datumIsporuke;
    }

    public Date getDatumIsporuke() {
        return datumIsporuke;
    }

    public void setDatumIsporuke(Date datumIsporuke) {
        this.datumIsporuke = datumIsporuke;
    }
}
