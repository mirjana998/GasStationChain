package com.example.lanacbenzinskihstanica;

public class RacunPartnera extends Racun {
    protected int idPartnera;

    public RacunPartnera(int idRacun, int bankaId, String brojRacuna, int idPartnera) {
        super(idRacun,bankaId,brojRacuna);
        this.idPartnera = idPartnera;
    }

    public RacunPartnera(RacunPartnera racunPartnera) {
        super(racunPartnera.getIdRacun(), racunPartnera.getBankaId(), racunPartnera.getBrojRacuna());
        this.idPartnera = racunPartnera.getIdPartnera();
    }

    public void setIdPartnera(int idPartnera) {
        this.idPartnera = idPartnera;
    }

    public int getIdPartnera() {
        return idPartnera;
    }

}
