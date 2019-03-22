-- MySQL Script generated by MySQL Workbench
-- Thu Mar 21 00:01:30 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Library` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Library` DEFAULT CHARACTER SET utf8 ;
USE `Library` ;

-- -----------------------------------------------------
-- Table `mydb`.`Reader`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Library`.`Reader` ;

CREATE TABLE IF NOT EXISTS `Library`.`Reader` (
  `reader_card` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `phone_number` INT NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`reader_card`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Book_Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Library`.`Book_Order` ;

CREATE TABLE IF NOT EXISTS `Library`.`Book_Order` (
  `order_id` INT NOT NULL,
  `book_isbn` VARCHAR(45) NOT NULL,
  `reader_card` INT NOT NULL,
  `date_of_loan` DATE NOT NULL,
  `date_of_return` DATE ,
  `how_much_days_was_on_loan` INT NOT NULL,
  PRIMARY KEY (`order_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Library`.`Book` ;

CREATE TABLE IF NOT EXISTS `Library`.`Book` (
  `isbn` VARCHAR(45) NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `annotation` VARCHAR(100) NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  `year_of_publishing` DATE NOT NULL,
  `book_count` INT NOT NULL,
  `key_words` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`isbn`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO Reader VALUES("1234-5555-6544", "Natalia Kiselyk", "nk@gmail.com","1234",0938258539,"Kyiv", "ADMIN");
INSERT INTO Reader VALUES ("6788-9877-3244", "Anita Borg", "banita@gmail.com", "qwwqq12", 0897654567, "Lviv", "READER");
INSERT INTO Reader VALUES("5768-0990-0000", "Stephan Korolko", "korolko@gmail.com", "uy788", 012345439, "Dnipro", "READER");
INSERT INTO Reader VALUES("8959-9889-1111","Oksana Frankiv", "oks_frankiv@gmail.com", "tt2111@", 0684356212, "Odessa", "READER");
INSERT INTO Reader VALUES("7688-7583-9999", "Orest Vatamanuk", "vatamaniuk@gmail.com", "7584", 0634567321, "Kharkiv", "READER");
INSERT INTO Reader VALUES("1234-5678-9000", "Steve Jobs", "s_jobs@gmail.com", "12wqqq", 0667895432, "Palo Alto", "READER");
INSERT INTO Reader VALUES("8809-0545-3000", "Halyna Voronina", "voronina_h@gmail.com", "rt4678", 0934567298, "Kyiv", "READER");

INSERT INTO Book VALUES("978-5-699-42553-2", "Gaston Leroux", "Phantom of the Opera", "On a stormy winter night a small plane bound for Boston goes down.", "G-1", "2017-06-15", 5, "phantom, novel, opera, Christine, Erik, Raule"); 
INSERT INTO Book VALUES("978-1640080447", "Vivian Schilling", "Quietus", "This eBook is for the use of anyone anywhere at no cost and with
almost no restrictions whatsoever.", "S-5", "2018-02-12", 2, "Kylie, scope, aftermath, hospital");
INSERT INTO Book VALUES("978-966-14-9952-1", "Джон Фаулз", "КОЛЕКЦIОНЕР", "Фредерик Клеґґ – нічим не примітний банківський клерк. Єдина його пристрасть – метелики. ", "Ф-3", "1963-12-12", 7, "чиновник, колекціонер, метелик");
INSERT INTO Book VALUES("978-943-14-1252-2", "Том Соєр", "Принц і злидар", "Я перекажу вам одну повість точнісінько так", "C-10", "2000-09-09", 50, "Том, принц, здидар, екземпляр");
INSERT INTO Book VALUES("978-617-585145-6", "Ліна Костенко", "Маруся Чурай", "«Маруся Чурай» — історичний роман у віршах Ліни Костенко, опублікований 1979 року. ", "K-3", "1979-03-12", 20, "Полтава, Ліна, Чурай, козаки");
INSERT INTO Book VALUES("978-617-679-391-2", "Ернест Хемінгвей", "Старий і море", "«Старий і море» — повість-притча американського письменника Ернеста Гемінґвея видана у 1952 році.", "Г-3", "2019-01-23", 7, "Сантьяго, море, рибак, здобич"); 
INSERT INTO Book VALUES("978-966-14-9952-22", "Джон Фаулз", "Коханка французького лейтенанта", "Джон Фаулз — уникальный писатель в литературе XX в", "Ф-23", "1963-09-09", 17, "чиновник, колекціонер");

INSERT INTO Book_Order VALUES(1, "978-5-699-42553-2", "1234-5555-6544", "2018-09-12", "2018-10-12", 31);
INSERT INTO Book_Order VALUES(2, "978-617-585145-6", "1234-5555-6544", "2019-01-21", null, 0);
INSERT INTO Book_Order VALUES(3, "978-966-14-9952-1", "8959-9889-1111", "2018-12-12", "2018-09-24", 20);
INSERT INTO Book_Order VALUES(4, "978-943-14-1252-2", "8809-0545-3000", "2019-02-01", "2019-02-21", 20);
INSERT INTO Book_Order VALUES(5, "978-617-679-391-2", "7688-7583-9999", "2018-04-23", null, 0);
INSERT INTO Book_Order VALUES(6, "978-617-585145-6", "5768-0990-0000", "2019-01-12", "2019-01-15", 3);
INSERT INTO Book_Order VALUES(7, "978-966-14-9952-1", "8959-9889-1111", "2018-12-12", "2018-01-24", 20);
INSERT INTO Book_Order VALUES(8, "978-943-14-1252-2", "8809-0545-3000", "2019-02-01", "2019-02-21", 20);
