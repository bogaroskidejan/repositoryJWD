package knjizara.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Knjiga")
public class Knjiga {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="pisac")
	private String pisac;
	
	@Column(name="ISBN")
	private String ISBN;
	
	@Column(name="kolicina")
	private int kolicina;
	
	@Column(name="cena")
	private double cena;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Izdavac izdavac;
	
	@OneToMany(mappedBy="knjiga",cascade=CascadeType.REMOVE)
	private List<Kupovina> kupovine = new ArrayList<Kupovina>();
	
	public Knjiga() {}

	public Knjiga(Long id, String naziv, String pisac, String iSBN, int kolicina, double cena, Izdavac izdavac) {
		this.id = id;
		this.naziv = naziv;
		this.pisac = pisac;
		ISBN = iSBN;
		this.kolicina = kolicina;
		this.cena = cena;
		this.izdavac = izdavac;
	}

	public Knjiga(String naziv, String pisac, String iSBN, int kolicina, double cena, Izdavac izdavac) {
		this.naziv = naziv;
		this.pisac = pisac;
		ISBN = iSBN;
		this.kolicina = kolicina;
		this.cena = cena;
		this.izdavac = izdavac;
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

	public String getPisac() {
		return pisac;
	}

	public void setPisac(String pisac) {
		this.pisac = pisac;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Izdavac getIzdavac() {
		return izdavac;
	}
	
	public void setIzdavac(Izdavac izdavac) {
		this.izdavac = izdavac;
		if(!izdavac.getKnjige().contains(this)){
			izdavac.getKnjige().add(this);
		}
	}

	public List<Kupovina> getKupovine() {
		return kupovine;
	}

	public void setKupovine(List<Kupovina> kupovine) {
		this.kupovine = kupovine;
	}
	
	public void addKupovina(Kupovina kupovina){ 
		this.kupovine.add(kupovina);
		if(!(kupovina.getKnjiga().equals(this))){ 
			kupovina.setKnjiga(this);
		}
	}
	
}
