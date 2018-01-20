DROP SCHEMA IF EXISTS `pilicarskiizbori`;
CREATE SCHEMA `pilicarskiizbori` DEFAULT CHARACTER SET utf8;
USE pilicarskiizbori;

CREATE TABLE lista (
	id INT AUTO_INCREMENT,
	slogan  VARCHAR(50) NOT NULL,
	brojGlasova  INT NOT NULL,
	sLice  VARCHAR(50),
	
	PRIMARY KEY(id)
);


CREATE TABLE stranka (
	id INT AUTO_INCREMENT,
	skracenica  VARCHAR(50) NOT NULL,
	naziv  VARCHAR(50) NOT NULL,
	idListe INT NOT NULL,
	
	PRIMARY KEY(id),
	
	FOREIGN KEY (idListe) REFERENCES lista(id)
	    ON DELETE RESTRICT
	
);


INSERT INTO lista (slogan, brojGlasova, sLice) VALUES ('Rasne koke pobeđuju',84,'Bela Mihalji');
INSERT INTO lista (slogan, brojGlasova, sLice) VALUES ('Da nam koke napreduju, da nose jaja',10,null);
INSERT INTO lista (slogan, brojGlasova, sLice) VALUES ('Služimo pilićarstvu',19,null);
INSERT INTO lista (slogan, brojGlasova, sLice) VALUES ('Jaja u sigurnim rukama',14,'Hristo Bonev');
INSERT INTO lista (slogan, brojGlasova, sLice) VALUES ('Sreća za pilice',7,null);
INSERT INTO lista (slogan, brojGlasova, sLice) VALUES ('Savez za bolje koke',8,null);
INSERT INTO lista (slogan, brojGlasova, sLice) VALUES ('Rasni i ekonomski stabilni',11,null);
INSERT INTO lista (slogan, brojGlasova, sLice) VALUES ('Kvalitetni ljudi uz kvalitetnu pilad',9,'Đorđe Čvarkov');
	
INSERT INTO stranka (skracenica,naziv, idListe) VALUES ('SNS','Sledbenici Nemačkih Sorti',1);
INSERT INTO stranka (skracenica,naziv, idListe) VALUES ('PUBS','Pokret uzgajivača Plimutroka Srbije ',1);
INSERT INTO stranka (skracenica,naziv, idListe) VALUES ('DS','Dorking Srbija',2);
INSERT INTO stranka (skracenica,naziv, idListe) VALUES ('SPS','Samostalni Pilićari Srbije',3);
INSERT INTO stranka (skracenica,naziv, idListe) VALUES ('JS','Jajari Srbije',3);
INSERT INTO stranka (skracenica,naziv, idListe) VALUES ('SRS','Srpski rasplodni savez',4);
INSERT INTO stranka (skracenica,naziv, idListe) VALUES ('DVERI','Držači Velikih Rasnih Italijanki',5);
INSERT INTO stranka (skracenica,naziv, idListe) VALUES ('DSS','Držači Somborske Svilene',5);
INSERT INTO stranka (skracenica,naziv, idListe) VALUES ('LDP','Lokalni Držači Peradi',6);
INSERT INTO stranka (skracenica,naziv, idListe) VALUES ('SDSk','Samostalni držači svilenih kokoši',6);
INSERT INTO stranka (skracenica,naziv, idListe) VALUES ('DJB','Domaća jaja Brama',7);
INSERT INTO stranka (skracenica,naziv, idListe) VALUES ('UJEBp','Ujedinjeni Bački Pilićari',8);

