package com.example.lanacbenzinskihstanica;

public class PoslovniPartnerPrikaz {
    protected int idPoslovniPartner;
    protected String jib;
    protected String nazivPoslovniP;
    protected String telefonPoslovniP;
    protected String adresaPoslovniP;
    protected String emailPoslovniP;
    protected String mjestoPoslovniP;
    protected String brojRacunaP;

    public PoslovniPartnerPrikaz() {
        super();
    }

    public PoslovniPartnerPrikaz(int id, String jib, String np, String tp, String ap, String ep, String mp, String br) {
        this.idPoslovniPartner = id;
        this.jib = jib;
        this.nazivPoslovniP = np;
        this.telefonPoslovniP = tp;
        this.adresaPoslovniP = ap;
        this.emailPoslovniP = ep ;
        this.mjestoPoslovniP = mp;
        this.brojRacunaP = br;
    }

    public PoslovniPartnerPrikaz(PoslovniPartnerPrikaz poslovniPartner) {
        this.idPoslovniPartner = poslovniPartner.getIdPoslovniPartner();
        this.jib = poslovniPartner.getJib();
        this.nazivPoslovniP = poslovniPartner.getNazivPoslovniP();
        this.telefonPoslovniP = poslovniPartner.getTelefonPoslovniP();
        this.adresaPoslovniP = poslovniPartner.getAdresaPoslovniP();
        this.emailPoslovniP = poslovniPartner.getEmailPoslovniP();
        this.mjestoPoslovniP = poslovniPartner.getMjestoPoslovniP();
        this.brojRacunaP = poslovniPartner.getBrojRacunaP();
    }

    public void setIdPoslovniPartner(int idPoslovniPartner) {
        this.idPoslovniPartner = idPoslovniPartner;
    }

    public int getIdPoslovniPartner() {
        return idPoslovniPartner;
    }

    public void setJib(String jib) {
        this.jib = jib;
    }

    public String getJib() {
        return jib;
    }

    public void setNazivPoslovniP(String nazivPoslovniP) {
        this.nazivPoslovniP = nazivPoslovniP;
    }

    public String getNazivPoslovniP() {
        return nazivPoslovniP;
    }

    public void setTelefonPoslovniP(String telefonPoslovniP) {
        this.telefonPoslovniP = telefonPoslovniP;
    }

    public String getTelefonPoslovniP() {
        return telefonPoslovniP;
    }

    public void setAdresaPoslovniP(String adresaPoslovniP) {
        this.adresaPoslovniP = adresaPoslovniP;
    }

    public String getAdresaPoslovniP() {
        return adresaPoslovniP;
    }

    public void setEmailPoslovniP(String emailPoslovniP) {
        this.emailPoslovniP = emailPoslovniP;
    }

    public String getEmailPoslovniP() {
        return emailPoslovniP;
    }

    public void setMjestoPoslovniP(String mjestoPoslovniP) {
        this.mjestoPoslovniP = mjestoPoslovniP;
    }

    public String getMjestoPoslovniP() {
        return mjestoPoslovniP;
    }

    public void setBrojRacunaP(String brojRacunaP) {
        this.mjestoPoslovniP = brojRacunaP;
    }

    public String getBrojRacunaP() {
        return brojRacunaP;
    }
 }
