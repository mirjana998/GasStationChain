package com.example.lanacbenzinskihstanica;

public class Artikal {
    protected  int idArtikal;
    protected String nazivArtikal;
    protected String mjernaJedinica;
    protected double cijenaArtikal;
    protected int zaliha;
    protected int benzinskaId;
    protected int kategorijaId;


    public Artikal(int id, String na, String mj, double c, int z, int bi, int ki) {
        this.idArtikal = id;
        this.nazivArtikal = na;
        this.mjernaJedinica = mj;
        this.cijenaArtikal = c;
        this.zaliha = z;
        this.benzinskaId = bi;
        this.kategorijaId = ki;
    }

    public Artikal(Artikal artikal) {
        this.idArtikal = artikal.getIdArtikal();
        this.nazivArtikal = artikal.getNazivArtikal();
        this.mjernaJedinica = artikal.getMjernaJedinica();
        this.cijenaArtikal = artikal.getCijenaArtikal();
        this.zaliha = artikal.getZaliha();
        this.benzinskaId = artikal.getBenzinskaId();
        this.kategorijaId = artikal.getKategorijaId();
    }

    public int getIdArtikal() {
        return idArtikal;
    }

    public void setIdArtikal(int idArtikal) {
        this.idArtikal = idArtikal;
    }

    public String getNazivArtikal() {
        return nazivArtikal;
    }

    public void setNazivArtikal(String nazivArtikal) {
        this.nazivArtikal = nazivArtikal;
    }

    public String getMjernaJedinica() {
        return mjernaJedinica;
    }

    public void setMjernaJedinica(String mjernaJedinica) {
        this.mjernaJedinica = mjernaJedinica;
    }

    public double getCijenaArtikal() {
        return cijenaArtikal;
    }

    public void setCijenaArtikal(double cijenaArtikal) {
        this.cijenaArtikal = cijenaArtikal;
    }

    public int getZaliha() {
        return zaliha;
    }

    public void setZaliha(int zaliha) {
        this.zaliha = zaliha;
    }

    public int getBenzinskaId() {
        return benzinskaId;
    }

    public void setBenzinskaId(int benzinskaId) {
        this.benzinskaId = benzinskaId;
    }

    public int getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(int kategorijaId) {
        this.kategorijaId = kategorijaId;
    }
}

