SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema jwts
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jwts` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `jwts` ;

-- -----------------------------------------------------
-- Table `jwts`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS`User` ;

CREATE TABLE IF NOT EXISTS .`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jwts`.`Product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Product` ;

CREATE TABLE IF NOT EXISTS `Product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `price` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jwts`.`ShoppingCartItem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ShoppingCartItem` ;

CREATE TABLE IF NOT EXISTS `ShoppingCartItem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `count` INT NULL,
  `User_id` INT NOT NULL,
  `Product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ShoppingCartItem_User_idx` (`User_id` ASC),
  INDEX `fk_ShoppingCartItem_Product1_idx` (`Product_id` ASC),
  CONSTRAINT `fk_ShoppingCartItem_User`
    FOREIGN KEY (`User_id`)
    REFERENCES `jwts`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ShoppingCartItem_Product1`
    FOREIGN KEY (`Product_id`)
    REFERENCES `jwts`.`Product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `jwts`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `jwts`;
INSERT INTO `User` (`id`, `username`, `password`) VALUES (NULL, 'pera', 'peric');
INSERT INTO `User` (`id`, `username`, `password`) VALUES (NULL, 'steva', 'stevic');

INSERT INTO `Product` (`id`, `name`, `price`) VALUES (NULL, 'Televizor marke Sony, 51 cm dijagonala', '22000');
INSERT INTO `Product` (`id`, `name`, `price`) VALUES (NULL, 'Sony digitalna kamera', '32000');
INSERT INTO `Product` (`id`, `name`, `price`) VALUES (NULL, 'Samsung monitor 17"', '35000');
INSERT INTO `Product` (`id`, `name`, `price`) VALUES (NULL, 'Pioneer DVD pisac', '7100');

COMMIT;

