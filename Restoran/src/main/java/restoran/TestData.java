package restoran;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restoran.model.Jelo;
import restoran.model.VrstaJela;
import restoran.service.JeloService;
import restoran.service.VrstaJelaService;

/*
DROP DATABASE IF EXISTS restoran;
CREATE DATABASE restoran DEFAULT CHARACTER SET utf8;

USE restoran;

GRANT ALL ON restoran.* TO 'restoran'@'%' IDENTIFIED BY 'restoran';

FLUSH PRIVILEGES;
 */

@Component
public class TestData {

	@Autowired
	private VrstaJelaService vrstaJelaService;
	
	@Autowired
	private JeloService jeloService;
	
	@PostConstruct
	public void init(){

		VrstaJela i1 = new VrstaJela();
		i1.setNaziv("Rostilj");
		i1.setOpis("Cumur");
		vrstaJelaService.save(i1);
		
		VrstaJela i2 = new VrstaJela();
		i2.setNaziv("Kuvana jela");
		i2.setOpis("Cumur");
		vrstaJelaService.save(i2);
		
		VrstaJela i3 = new VrstaJela();
		i3.setNaziv("Desert");
		i3.setOpis("Kolac");
		vrstaJelaService.save(i3);
		
		VrstaJela i4 = new VrstaJela();
		i4.setNaziv("Specijalitet kuce");
		i4.setOpis("specijalitet");
		vrstaJelaService.save(i4);
		
		Jelo k1 = new Jelo();
		k1.setNaziv("Pita sa pomorandzama");
		k1.setCena(1100.00);
		k1.setVrstaJela(i4);
		jeloService.save(k1);
		
		Jelo k2 = new Jelo();
		k2.setNaziv("10 cevapcica u somunu");
		k2.setCena(430.00);
		k2.setVrstaJela(i1);
		jeloService.save(k2);
		
		Jelo k3 = new Jelo();
		k3.setNaziv("Dimljena vesalica");
		k3.setCena(520.00);
		k3.setVrstaJela(i1);
		jeloService.save(k3);
		
		Jelo k4 = new Jelo();
		k4.setNaziv("Gulas");
		k4.setCena(500.00);
		k4.setVrstaJela(i2);
		jeloService.save(k4);
		
		Jelo k5 = new Jelo();
		k5.setNaziv("Zaher Torta");
		k5.setCena(150.00);
		k5.setVrstaJela(i3);
		jeloService.save(k5);
		
	}
}
