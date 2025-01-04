-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lanac_benzinskih_pumpi
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lanac_benzinskih_pumpi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lanac_benzinskih_pumpi` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `lanac_benzinskih_pumpi` ;

-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`kategorija`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`kategorija` (
  `id` INT NOT NULL,
  `opis` VARCHAR(100) NULL,
  `naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`mjesto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`mjesto` (
  `id` INT NOT NULL,
  `naziv` VARCHAR(45) NOT NULL,
  `broj_poste` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `broj_poste_UNIQUE` (`broj_poste` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`benzinska_stanica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`benzinska_stanica` (
  `id` INT NOT NULL,
  `adresa` VARCHAR(100) NOT NULL,
  `telefon` VARCHAR(45) NOT NULL,
  `mjesto_id` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_BENZINSKA_STANICA_MJESTO1_idx` (`mjesto_id` ASC) VISIBLE,
  CONSTRAINT `fk_BENZINSKA_STANICA_MJESTO1`
    FOREIGN KEY (`mjesto_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`mjesto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`artikal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`artikal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  `cijena` DECIMAL(7,3) NOT NULL,
  `mjerna_jedinica` VARCHAR(45) NOT NULL,
  `kategorija_id` INT NOT NULL,
  `benzinska_stanica_id` INT NOT NULL,
  `zaliha` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `IdArtikla_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_artikal_kategorija1_idx` (`kategorija_id` ASC) VISIBLE,
  INDEX `fk_artikal_benzinska_stanica1_idx` (`benzinska_stanica_id` ASC) VISIBLE,
  UNIQUE INDEX `naziv_UNIQUE` (`naziv` ASC) VISIBLE,
  CONSTRAINT `fk_artikal_kategorija1`
    FOREIGN KEY (`kategorija_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`kategorija` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_artikal_benzinska_stanica1`
    FOREIGN KEY (`benzinska_stanica_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`benzinska_stanica` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`uloga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`uloga` (
  `opis` VARCHAR(100) NOT NULL,
  `naziv` VARCHAR(45) NOT NULL,
  `id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`zaposlenik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`zaposlenik` (
  `jmbg` CHAR(13) NOT NULL,
  `ime` VARCHAR(45) NOT NULL,
  `prezime` VARCHAR(45) NOT NULL,
  `benzinska_stanica_id` INT NOT NULL,
  `datum_zaposlenja` DATE NOT NULL,
  `id` INT NOT NULL,
  `datum_prestanka` DATE NOT NULL,
  `korisnicko_ime` VARCHAR(45) NOT NULL,
  `lozinka` VARCHAR(45) NOT NULL,
  `mjesto_id` INT NOT NULL,
  `adresa` VARCHAR(100) NOT NULL,
  `uloga_id` INT NOT NULL,
  `u_radnom_odnosu` TINYINT(1) NULL,
  INDEX `fk_ZAPOSLENI_BENZINSKA_STANICA1_idx` (`benzinska_stanica_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `JMBG_UNIQUE` (`jmbg` ASC) VISIBLE,
  INDEX `fk_zaposleni_mjesto1_idx` (`mjesto_id` ASC) VISIBLE,
  INDEX `fk_zaposlenik_uloga1_idx` (`uloga_id` ASC) VISIBLE,
  CONSTRAINT `fk_ZAPOSLENI_BENZINSKA_STANICA1`
    FOREIGN KEY (`benzinska_stanica_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`benzinska_stanica` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_zaposleni_mjesto1`
    FOREIGN KEY (`mjesto_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`mjesto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_zaposlenik_uloga1`
    FOREIGN KEY (`uloga_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`uloga` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`poslovni_partner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`poslovni_partner` (
  `jib` VARCHAR(13) NOT NULL,
  `naziv` VARCHAR(45) NOT NULL,
  `telefon` VARCHAR(45) NOT NULL,
  `adresa` VARCHAR(100) NOT NULL,
  `mjesto_id` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_DOBAVLJAC_MJESTO1_idx` (`mjesto_id` ASC) VISIBLE,
  UNIQUE INDEX `JIB_UNIQUE` (`jib` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_DOBAVLJAC_MJESTO1`
    FOREIGN KEY (`mjesto_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`mjesto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`tip_placanja`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`tip_placanja` (
  `id` INT NOT NULL,
  `tip_placanja` VARCHAR(45) NOT NULL,
  `opis` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`banka`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`banka` (
  `id` INT NOT NULL,
  `mjesto_id` INT NOT NULL,
  `naziv` VARCHAR(45) NOT NULL,
  `adresa` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_banka_mjesto1_idx` (`mjesto_id` ASC) VISIBLE,
  CONSTRAINT `fk_banka_mjesto1`
    FOREIGN KEY (`mjesto_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`mjesto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`racun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`racun` (
  `id` INT NOT NULL,
  `banka_id` INT NOT NULL,
  `broj_racuna` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_racun_banka1_idx` (`banka_id` ASC) VISIBLE,
  UNIQUE INDEX `broj_racuna_UNIQUE` (`broj_racuna` ASC) VISIBLE,
  CONSTRAINT `fk_racun_banka1`
    FOREIGN KEY (`banka_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`banka` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`racun_partnera`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`racun_partnera` (
  `racun_id` INT NOT NULL,
  `poslovni_partner_id` INT NOT NULL,
  PRIMARY KEY (`racun_id`),
  INDEX `fk_racun_dobavljaca_racun1_idx` (`racun_id` ASC) VISIBLE,
  INDEX `fk_racun_dobavljaca_dobavljac1_idx` (`poslovni_partner_id` ASC) VISIBLE,
  CONSTRAINT `fk_racun_dobavljaca_racun1`
    FOREIGN KEY (`racun_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`racun` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_racun_dobavljaca_dobavljac1`
    FOREIGN KEY (`poslovni_partner_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`poslovni_partner` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`faktura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`faktura` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datum_izdavanja` DATE NOT NULL,
  `ukupno` DECIMAL(7,3) NOT NULL,
  `zaposlenik_id` INT NOT NULL,
  `benzinska_stanica_id` INT NOT NULL,
  `tip_placanja_id` INT NOT NULL,
  `komentar` VARCHAR(100) NULL,
  `racun_partnera_racun_id` INT NULL,
  `poslovni_partner_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_faktura_zaposleni1_idx` (`zaposlenik_id` ASC) VISIBLE,
  INDEX `fk_faktura_benzinska_stanica1_idx` (`benzinska_stanica_id` ASC) VISIBLE,
  INDEX `fk_faktura_tip_placanja1_idx` (`tip_placanja_id` ASC) VISIBLE,
  INDEX `fk_faktura_racun_partnera1_idx` (`racun_partnera_racun_id` ASC) VISIBLE,
  INDEX `fk_faktura_poslovni_partner1_idx` (`poslovni_partner_id` ASC) VISIBLE,
  CONSTRAINT `fk_faktura_zaposleni1`
    FOREIGN KEY (`zaposlenik_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`zaposlenik` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_faktura_benzinska_stanica1`
    FOREIGN KEY (`benzinska_stanica_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`benzinska_stanica` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_faktura_tip_placanja1`
    FOREIGN KEY (`tip_placanja_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`tip_placanja` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_faktura_racun_partnera1`
    FOREIGN KEY (`racun_partnera_racun_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`racun_partnera` (`racun_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_faktura_poslovni_partner1`
    FOREIGN KEY (`poslovni_partner_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`poslovni_partner` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`ulazna_faktura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`ulazna_faktura` (
  `faktura_id` INT NOT NULL,
  `datum_isporuke` DATE NOT NULL,
  PRIMARY KEY (`faktura_id`),
  CONSTRAINT `fk_ulazna_faktura_faktura1`
    FOREIGN KEY (`faktura_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`faktura` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`izlazna_faktura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`izlazna_faktura` (
  `faktura_id` INT NOT NULL,
  `datum_placanja` DATE NOT NULL,
  PRIMARY KEY (`faktura_id`),
  CONSTRAINT `fk_izlazna_faktura_faktura1`
    FOREIGN KEY (`faktura_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`faktura` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`stavka_fakture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`stavka_fakture` (
  `faktura_id` INT NOT NULL,
  `artikal_id` INT NOT NULL,
  `kolicina` INT NOT NULL,
  `cijena` DECIMAL(7,3) NOT NULL,
  PRIMARY KEY (`faktura_id`, `artikal_id`),
  INDEX `fk_stavka_fakture_artikal1_idx` (`artikal_id` ASC) VISIBLE,
  INDEX `fk_stavka_fakture_faktura1_idx` (`faktura_id` ASC) VISIBLE,
  CONSTRAINT `fk_stavka_fakture_artikal1`
    FOREIGN KEY (`artikal_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`artikal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_stavka_fakture_faktura1`
    FOREIGN KEY (`faktura_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`faktura` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`racun_poslovnice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`racun_poslovnice` (
  `racun_id` INT NOT NULL,
  `benzinska_stanica_id` INT NOT NULL,
  PRIMARY KEY (`racun_id`),
  INDEX `fk_racun_poslovnice_racun1_idx` (`racun_id` ASC) VISIBLE,
  INDEX `fk_racun_poslovnice_benzinska_stanica1_idx` (`benzinska_stanica_id` ASC) VISIBLE,
  CONSTRAINT `fk_racun_poslovnice_racun1`
    FOREIGN KEY (`racun_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`racun` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_racun_poslovnice_benzinska_stanica1`
    FOREIGN KEY (`benzinska_stanica_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`benzinska_stanica` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lanac_benzinskih_pumpi`.`ugovor_o_radu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lanac_benzinskih_pumpi`.`ugovor_o_radu` (
  `id` INT NOT NULL,
  `datum_od` DATE NOT NULL,
  `datum_do` DATE NOT NULL,
  `zaposlenik_id` INT NOT NULL,
  `datum_potpisivanja` DATE NOT NULL,
  `plata` DECIMAL(7,3) NOT NULL,
  `benzinska_stanica_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ugovor_o_radu_zaposlenik1_idx` (`zaposlenik_id` ASC) VISIBLE,
  INDEX `fk_ugovor_o_radu_benzinska_stanica1_idx` (`benzinska_stanica_id` ASC) VISIBLE,
  CONSTRAINT `fk_ugovor_o_radu_zaposlenik1`
    FOREIGN KEY (`zaposlenik_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`zaposlenik` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ugovor_o_radu_benzinska_stanica1`
    FOREIGN KEY (`benzinska_stanica_id`)
    REFERENCES `lanac_benzinskih_pumpi`.`benzinska_stanica` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
