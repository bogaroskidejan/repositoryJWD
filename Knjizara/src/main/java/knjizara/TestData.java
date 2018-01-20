package knjizara;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import knjizara.model.Izdavac;
import knjizara.model.Knjiga;
import knjizara.service.IzdavacService;
import knjizara.service.KnjigaService;

/*	
DROP DATABASE IF EXISTS knjizara;
CREATE DATABASE knjizara DEFAULT CHARACTER SET utf8;

USE knjizara;

GRANT ALL ON knjizara.* TO 'knjizara'@'%' IDENTIFIED BY 'knjizara';

FLUSH PRIVILEGES;
*/

@Component
public class TestData {
	
	@Autowired
	private KnjigaService knjigaService;
	
	@Autowired
	private IzdavacService izdavacService;
	
	@PostConstruct
	public void init(){
		
		Izdavac i1 = new Izdavac();
		i1.setNaziv("Polet");
		i1.setEmail("polet@gmail.com");
		i1.setTelefon("0667612312");
		izdavacService.save(i1);
		
		Izdavac i2 = new Izdavac();
		i2.setNaziv("Delfi");
		i2.setEmail("delfi@gmail.com");
		i2.setTelefon("0661233211");
		izdavacService.save(i2);
		
		Knjiga k1 = new Knjiga();
		k1.setISBN("1100110011");
		k1.setNaziv("Faust");
		k1.setKolicina(10);
		k1.setPisac("Johan Volfgang Gete");
		k1.setIzdavac(i2);
		k1.setCena(1231.00);
		knjigaService.save(k1);
		
		Knjiga k2 = new Knjiga();
		k2.setISBN("12312312");
		k2.setNaziv("Tako je govorio Zaratustra");
		k2.setKolicina(15);
		k2.setPisac("Fridrih Nice");
		k2.setIzdavac(i1);
		k2.setCena(1250.00);
		knjigaService.save(k2);
		
		Knjiga k3 = new Knjiga();
		k3.setISBN("12312432");
		k3.setNaziv("Idiot");
		k3.setKolicina(31);
		k3.setPisac("Fjodor Mihajlovic Dostojevski");
		k3.setIzdavac(i1);
		k3.setCena(1400.00);
		knjigaService.save(k3);
		
		Knjiga k4 = new Knjiga();
		k4.setISBN("42312312");
		k4.setNaziv("Ubistvo u Orijent ekspresu");
		k4.setKolicina(15);
		k4.setPisac("Agata Kristi");
		k4.setIzdavac(i1);
		k4.setCena(950.00);
		knjigaService.save(k4);
		
		Knjiga k5 = new Knjiga();
		k5.setISBN("53252312");
		k5.setNaziv("Price o gradovima");
		k5.setKolicina(14);
		k5.setPisac("Ivo Andric");
		k5.setIzdavac(i2);
		k5.setCena(1100.00);
		knjigaService.save(k5);
		
	}
}
