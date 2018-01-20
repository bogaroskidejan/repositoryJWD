DROP SCHEMA IF EXISTS `menjacnica`;
CREATE SCHEMA `menjacnica` DEFAULT CHARACTER SET utf8;
USE menjacnica;

DROP TABLE IF EXISTS valuta;
DROP TABLE IF EXISTS kursnaLista;

CREATE TABLE valuta (
	oznaka VARCHAR(3) NOT NULL,
    nazivValute VARCHAR(10) NOT NULL,
    
    PRIMARY KEY (oznaka)
);

CREATE TABLE kursnaLista (
	datumFormiranja DATE NOT NULL,
	
    PRIMARY KEY (datumFormiranja)
);

CREATE TABLE vrednostValute (
	kupovni INT NOT NULL,
    prodajni INT NOT NULL,
    datumListe DATE NOT NULL,
    valuta VARCHAR (3),
    
    PRIMARY KEY (datumListe, valuta),
    FOREIGN KEY (datumListe) REFERENCES kursnaLista(datumFormiranja)
    ON DELETE RESTRICT
);

INSERT INTO valuta (oznaka, nazivValute) VALUES ('EUR','euro');
INSERT INTO valuta (oznaka, nazivValute) VALUES ('USD','dolar');
INSERT INTO valuta (oznaka, nazivValute) VALUES ('CHF','frank');
INSERT INTO valuta (oznaka, nazivValute) VALUES ('YEN','jen');

INSERT INTO kursnaLista (datumFormiranja) VALUES ('2017-01-01');
INSERT INTO kursnaLista (datumFormiranja) VALUES ('2017-02-01');
INSERT INTO kursnaLista (datumFormiranja) VALUES ('2017-03-01');

INSERT INTO vrednostValute (valuta, datumListe, kupovni, prodajni) VALUES ('EUR', '2017-01-01', 112.5, 113);
INSERT INTO vrednostValute (valuta, datumListe, kupovni, prodajni) VALUES ('EUR', '2017-02-01', 112, 114);
INSERT INTO vrednostValute (valuta, datumListe, kupovni, prodajni) VALUES ('EUR', '2017-03-01', 113, 115);
INSERT INTO vrednostValute (valuta, datumListe, kupovni, prodajni) VALUES ('USD', '2017-01-01', 109.5, 111);
INSERT INTO vrednostValute (valuta, datumListe, kupovni, prodajni) VALUES ('USD', '2017-02-01', 109 , 110);
INSERT INTO vrednostValute (valuta, datumListe, kupovni, prodajni) VALUES ('USD', '2017-03-01', 112, 110.5);
INSERT INTO vrednostValute (valuta, datumListe, kupovni, prodajni) VALUES ('CHF', '2017-01-01', 125.2, 128);
INSERT INTO vrednostValute (valuta, datumListe, kupovni, prodajni) VALUES ('CHF', '2017-02-01', 123.8, 125.4);
INSERT INTO vrednostValute (valuta, datumListe, kupovni, prodajni) VALUES ('CHF', '2017-03-01', 124, 126.3);
INSERT INTO vrednostValute (valuta, datumListe, kupovni, prodajni) VALUES ('YEN', '2017-01-01', 115, 118.6);
INSERT INTO vrednostValute (valuta, datumListe, kupovni, prodajni) VALUES ('YEN', '2017-02-01', 116.5, 118.9);
INSERT INTO vrednostValute (valuta, datumListe, kupovni, prodajni) VALUES ('YEN', '2017-03-01', 118, 120.2);
