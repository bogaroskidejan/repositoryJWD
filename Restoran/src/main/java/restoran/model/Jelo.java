package restoran.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="Jelo")
public class Jelo {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="cena")
	private double cena;
	
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date datumAzuriranja = new Date();
	
	@ManyToOne(fetch=FetchType.LAZY)
	private VrstaJela vrstaJela;
	
	public Jelo() {}

	public Jelo(Long id, String naziv, double cena, Date datumAzuriranja, VrstaJela vrstaJela) {
		this.id = id;
		this.naziv = naziv;
		this.cena = cena;
		this.datumAzuriranja = datumAzuriranja;
		this.vrstaJela = vrstaJela;
	}

	public Jelo(String naziv, double cena, Date datumAzuriranja, VrstaJela vrstaJela) {
		this.naziv = naziv;
		this.cena = cena;
		this.datumAzuriranja = datumAzuriranja;
		this.vrstaJela = vrstaJela;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Date getDatumAzuriranja() {
		return datumAzuriranja;
	}

	public void setDatumAzuriranja(Date datumAzuriranja) {
		this.datumAzuriranja = datumAzuriranja;
	}

	public VrstaJela getVrstaJela() {
		return vrstaJela;
	}

	public void setVrstaJela(VrstaJela vrstaJela) {
		this.vrstaJela = vrstaJela;
		if(!vrstaJela.getJela().contains(this)){
			vrstaJela.getJela().add(this);
		}
	}
	
}
