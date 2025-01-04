package com.example.lanacbenzinskihstanica;

public class BenzinskaStanica {
        protected int idBenzinska;
        protected String adresaBenzinska;
        protected String emailBenzinska;
        protected String telefonBenzinska;
        protected int mjestoId;

        public BenzinskaStanica() {
            super();
        }

        public BenzinskaStanica(int idBenzinska, String adresaBenzinska, String emailBenzinska, String telefonBenzinska, int mjestoId) {
            this.idBenzinska = idBenzinska;
            this.adresaBenzinska = adresaBenzinska;
            this.emailBenzinska = emailBenzinska;
            this.telefonBenzinska = telefonBenzinska;
            this.mjestoId = mjestoId;
        }

        public BenzinskaStanica(BenzinskaStanica benzinskaStanica) {
            this.idBenzinska = benzinskaStanica.getIdBenzinska();
            this.adresaBenzinska = benzinskaStanica.getAdresaBenzinska();
            this.emailBenzinska = benzinskaStanica.getEmailBenzinska();
            this.telefonBenzinska = benzinskaStanica.getTelefonBenzinska();
            this.mjestoId = benzinskaStanica.getMjestoBenzinska();
        }

        public void setIdBenzinska(int idBenzinska) {
            this.idBenzinska = idBenzinska;
        }

        public int getIdBenzinska() {
            return idBenzinska;
        }

        public void setAdresaBenzinska(String adresaBenzinska) {
            this.adresaBenzinska = adresaBenzinska;
        }

        public String getAdresaBenzinska() {
            return adresaBenzinska;
        }

        public void setEmailBenzinska(String emailBenzinska) {
            this.emailBenzinska = emailBenzinska;
        }

        public String getEmailBenzinska() {
            return emailBenzinska;
        }

        public void setTelefonBenzinska(String telefonBenzinska) {
            this.telefonBenzinska = telefonBenzinska;
        }

        public String getTelefonBenzinska() {
            return telefonBenzinska;
        }

        public void setMjestoId(int mjestoId) {
            this.mjestoId = mjestoId;
        }

        public int getMjestoBenzinska() {
            return mjestoId;
        }
    }

