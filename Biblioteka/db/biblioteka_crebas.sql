
-- -----------------------------------------------------
-- Schema biblioteka
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `biblioteka` ;

-- -----------------------------------------------------
-- Schema biblioteka
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `biblioteka` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `biblioteka` ;

-- -----------------------------------------------------
-- Table `biblioteka`.`Knjiga`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteka`.`Knjiga`;

-- -----------------------------------------------------
-- Table `biblioteka`.`Autor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `biblioteka`.`Autor` ;

CREATE TABLE IF NOT EXISTS `biblioteka`.`Autor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ime` VARCHAR(45) NULL,
  `prezime` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Autor_UNIQUE` (`id` ASC))
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `biblioteka`.`Knjiga` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `naslov` VARCHAR(45) NULL,
  `id_autora` INT NOT NULL,
  PRIMARY KEY (`id`),
	CONSTRAINT `fk_KnjigaAutor_Knjiga`
    FOREIGN KEY (`id_autora`)
    REFERENCES `biblioteka`.`autor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `biblioteka`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Data for table `biblioteka`.`Autor`
-- -----------------------------------------------------
START TRANSACTION;
USE `biblioteka`;
INSERT INTO `biblioteka`.`Autor` (`id`, `ime`, `prezime`) VALUES (1, 'Franc', 'Kafka');
INSERT INTO `biblioteka`.`Autor` (`id`, `ime`, `prezime`) VALUES (2, 'Zil', 'Delez');
INSERT INTO `biblioteka`.`Autor` (`id`, `ime`, `prezime`) VALUES (3, 'Feliks', 'Gatari');

COMMIT;



-- -----------------------------------------------------
-- Data for table `biblioteka`.`Knjiga`
-- -----------------------------------------------------
START TRANSACTION;
USE `biblioteka`;
INSERT INTO `biblioteka`.`Knjiga` (`id`, `naslov`, `id_autora` ) VALUES (1, 'Proces', 1);
INSERT INTO `biblioteka`.`Knjiga` (`id`, `naslov`, `id_autora`) VALUES (2, 'Hiljadu ravni', 2);
INSERT INTO `biblioteka`.`Knjiga` (`id`, `naslov`, `id_autora`) VALUES (3, 'Sta je filozofija?', 3);

COMMIT;

-- -----------------------------------------------------
-- Data for table `jwts`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `biblioteka`;
INSERT INTO `biblioteka`.`User` (`id`, `username`, `password`) VALUES (NULL, 'pera', 'peric');
INSERT INTO `biblioteka`.`User` (`id`, `username`, `password`) VALUES (NULL, 'steva', 'stevic');

COMMIT;

