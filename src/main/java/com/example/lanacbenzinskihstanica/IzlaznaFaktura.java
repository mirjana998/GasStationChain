package com.example.lanacbenzinskihstanica;

import java.util.Date;

    public class IzlaznaFaktura extends Faktura {
        protected Date datumPlacanja;

        public IzlaznaFaktura(int idFaktura, String komentar, double ukupno, Date datumIzdavanja, int zaposlenikId, int benzinskaStanicaId, int tipPlacanjaId, int racunPartneraId, int poslovniPartnerId, Date datumPlacanja) {
            super(idFaktura, komentar, ukupno, datumIzdavanja, zaposlenikId, benzinskaStanicaId, tipPlacanjaId, racunPartneraId, poslovniPartnerId);
            this.datumPlacanja = datumPlacanja;
        }

        public Date getDatumPlacanja() {
            return datumPlacanja;
        }
        public void setDatumPlacanja(Date datumPlacanja) {

            this.datumPlacanja = datumPlacanja;
        }
    }