-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema taskdidattici
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema taskdidattici
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `taskdidattici` DEFAULT CHARACTER SET utf8mb3 ;
USE `taskdidattici` ;

-- -----------------------------------------------------
-- Table `taskdidattici`.`docenti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taskdidattici`.`docenti` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `cognome` VARCHAR(45) NULL DEFAULT NULL,
  `mail` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `mail_UNIQUE` (`mail` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `taskdidattici`.`classi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taskdidattici`.`classi` (
  `codice` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `numeroTask` INT NULL DEFAULT NULL,
  `docente_id` INT NULL,
  PRIMARY KEY (`codice`),
  INDEX `fk_classe_docente1_idx` (`docente_id` ASC) VISIBLE,
  CONSTRAINT `fk_classe_docente1`
    FOREIGN KEY (`docente_id`)
    REFERENCES `taskdidattici`.`docenti` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `taskdidattici`.`studenti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taskdidattici`.`studenti` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `cognome` VARCHAR(45) NULL DEFAULT NULL,
  `mail` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `numTaskCompletati` INT NULL DEFAULT NULL,
  `numTaskValutati` INT NULL DEFAULT NULL,
  `punteggioTotaleOttenuto` INT NULL DEFAULT NULL,
  `classe_codice` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_studente_classe1_idx` (`classe_codice` ASC) VISIBLE,
  UNIQUE INDEX `mail_UNIQUE` (`mail` ASC) VISIBLE,
  CONSTRAINT `fk_studente_classe1`
    FOREIGN KEY (`classe_codice`)
    REFERENCES `taskdidattici`.`classi` (`codice`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `taskdidattici`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taskdidattici`.`task` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titolo` VARCHAR(45) NULL DEFAULT NULL,
  `descrizione` VARCHAR(150) NULL DEFAULT NULL,
  `dataScadenza` VARCHAR(10) NULL DEFAULT NULL,
  `maxPuntiAssegnabili` INT NULL DEFAULT NULL,
  `classe_codice` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_task_classe1_idx` (`classe_codice` ASC) VISIBLE,
  CONSTRAINT `fk_task_classe1`
    FOREIGN KEY (`classe_codice`)
    REFERENCES `taskdidattici`.`classi` (`codice`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `taskdidattici`.`consegne`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taskdidattici`.`consegne` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `punteggio` INT NULL DEFAULT NULL,
  `soluzione` BLOB NULL DEFAULT NULL,
  `task_id` INT NOT NULL,
  `studente_id` INT NOT NULL,
  INDEX `fk_consegna_task1_idx` (`task_id` ASC) VISIBLE,
  INDEX `fk_consegna_studente1_idx` (`studente_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_consegna_studente1`
    FOREIGN KEY (`studente_id`)
    REFERENCES `taskdidattici`.`studenti` (`id`),
  CONSTRAINT `fk_consegna_task1`
    FOREIGN KEY (`task_id`)
    REFERENCES `taskdidattici`.`task` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
